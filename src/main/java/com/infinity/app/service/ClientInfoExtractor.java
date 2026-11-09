package com.infinity.app.service;
import jakarta.servlet.http.HttpServletRequest; // use javax.servlet.http.HttpServletRequest for Servlet 4.x / Spring Boot 2.x

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts client IP address, hostname and browser from an HttpServletRequest.
 *
 * Usage:
 *   ClientInfoExtractor info = new ClientInfoExtractor(request);
 *   String ip      = info.getIpAddress();
 *   String host    = info.getHostname();
 *   String browser = info.getBrowser();
 */
public class ClientInfoExtractor {

    /** Headers commonly set by proxies / load balancers, checked in order. */
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
            "CF-Connecting-IP"   // Cloudflare
    };

    /**
     * Browser detection rules. ORDER MATTERS: many browsers include
     * "Chrome" and "Safari" in their User-Agent, so the more specific
     * ones must be tested first.
     */
    private static final String[][] BROWSER_RULES = {
            // {display name, version regex}
            {"Edge",              "(?:Edg|EdgA|EdgiOS|Edge)/([\\d.]+)"},
            {"Opera",             "(?:OPR|Opera)/([\\d.]+)"},
            {"Samsung Internet",  "SamsungBrowser/([\\d.]+)"},
            {"Vivaldi",           "Vivaldi/([\\d.]+)"},
            {"Firefox",           "(?:Firefox|FxiOS)/([\\d.]+)"},
            {"Chrome",            "(?:Chrome|CriOS)/([\\d.]+)"},
            {"Internet Explorer", "(?:MSIE |rv:)([\\d.]+)(?=.*Trident|\\))"},
            {"Safari",            "Version/([\\d.]+).*Safari/"}
    };

    private final HttpServletRequest request;

    private String ipAddress;
    private String hostname;
    private String browser;

    public ClientInfoExtractor(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request must not be null");
        }
        this.request = request;
    }

    // ------------------------------------------------------------------ IP

    /** Returns the client's IP address, honouring common proxy headers. */
    public String getIpAddress() {
        if (ipAddress == null) {
            ipAddress = resolveIpAddress();
        }
        return ipAddress;
    }

    private String resolveIpAddress() {
        for (String header : IP_HEADERS) {
            String value = request.getHeader(header);
            if (isValid(value)) {
                // X-Forwarded-For may be "client, proxy1, proxy2" -> take the first
                String ip = value.split(",")[0].trim();
                if (isValid(ip)) {
                    return normalize(ip);
                }
            }
        }
        return normalize(request.getRemoteAddr());
    }

    // ------------------------------------------------------------ Hostname

    /**
     * Returns the client's hostname via reverse DNS lookup.
     * Falls back to the IP address if it cannot be resolved.
     *
     * Note: reverse DNS can be slow; cache the result if you call this
     * on every request.
     */
    public String getHostname() {
        if (hostname == null) {
            hostname = resolveHostname();
        }
        return hostname;
    }

    private String resolveHostname() {
        String ip = getIpAddress();
        try {
            return InetAddress.getByName(ip).getCanonicalHostName();
        } catch (UnknownHostException e) {
            return ip;
        }
    }

    // ------------------------------------------------------------- Browser

    /** Returns the browser name and version, e.g. "Chrome 126.0.0.0". */
    public String getBrowser() {
        if (browser == null) {
            browser = resolveBrowser();
        }
        return browser;
    }

    private String resolveBrowser() {
        String userAgent = request.getHeader("User-Agent");
        if (!isValid(userAgent)) {
            return "Unknown";
        }

        for (String[] rule : BROWSER_RULES) {
            Matcher m = Pattern.compile(rule[1]).matcher(userAgent);
            if (m.find()) {
                return rule[0] + " " + m.group(1);
            }
        }

        // Non-browser clients
        String ua = userAgent.toLowerCase();
        if (ua.startsWith("curl"))   return "curl";
        if (ua.contains("postman"))  return "Postman";
        if (ua.contains("okhttp"))   return "OkHttp";
        if (ua.contains("java"))     return "Java HTTP Client";
        if (ua.contains("bot") || ua.contains("spider") || ua.contains("crawl")) return "Bot/Crawler";

        return "Unknown";
    }

    // ------------------------------------------------------------- Helpers

    private static boolean isValid(String value) {
        return value != null && !value.trim().isEmpty() && !"unknown".equalsIgnoreCase(value.trim());
    }

    /** Converts IPv6 loopback to the familiar IPv4 form. */
    private static String normalize(String ip) {
        if (ip == null) {
            return "unknown";
        }
        return ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) ? "127.0.0.1" : ip;
    }

    @Override
    public String toString() {
        return "ClientInfo{ip='" + getIpAddress() + "', hostname='" + getHostname()
                + "', browser='" + getBrowser() + "'}";
    }
}
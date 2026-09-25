package com.infinity.app.security;

/**
 * Canonical role names, matching app_role.name in the database and the
 * "roles" claim embedded in the JWT at login. Spring's hasRole("X") checks
 * against the authority "ROLE_X", so these plain names (without the prefix)
 * are what's stored everywhere; the "ROLE_" prefix is added only where
 * Spring Security expects it (see JwtAuthenticationFilter).
 */
public final class RoleNames {
    public static final String LOG_ISSUE    = "LOG_ISSUE";     // Log an Issue
    public static final String DISPLAY      = "DISPLAY";       // Use Application Backend > Display
    public static final String INSERT       = "INSERT";        // Use Application Backend > Insert
    public static final String UPDATE       = "UPDATE";        // Use Application Backend > Update
    public static final String LOGGED_CALLS = "LOGGED_CALLS";  // Logged Calls

    private RoleNames() {}
}

package com.infinity.app.dto;

public class LoginRequest {
    private String cn;
    private String password;

    public String getCn() { return cn; }
    public void setCn(String cn) { this.cn = cn; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

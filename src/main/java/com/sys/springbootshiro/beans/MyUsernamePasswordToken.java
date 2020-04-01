package com.sys.springbootshiro.beans;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken {
    private String systemflg ;

    public MyUsernamePasswordToken(String username, String password, String systemflg) {
        super(username, password);
        this.systemflg = systemflg;
    }

    public String getSystemflg() {
        return systemflg;
    }

    public void setSystemflg(String systemflg) {
        this.systemflg = systemflg;
    }
}

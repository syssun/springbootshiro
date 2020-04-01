package com.sys.springbootshiro.beans;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userName;
    private String userid ;
    private String password ;

    private Set<Role> roles;

    public User(String userName) {
        this.userName = userName;
        this.password="123";
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());
        this.roles = roles ;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.sys.springbootshiro.beans;

public class Permissions {
    private String id;
    private String permissionsName;

    public Permissions() {
        this.id = "id";
        this.permissionsName = "user:add";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }
}

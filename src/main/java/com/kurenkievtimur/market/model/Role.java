package com.kurenkievtimur.market.model;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.READ_AUTHORITY, Permission.UPDATE_AUTHORITY, Permission.DELETE_AUTHORITY, Permission.WRITE_AUTHORITY)),
    EMPLOYEE(Set.of(Permission.READ_AUTHORITY));

    private final Set<Permission> permission;

    Role(Set<Permission> permission) {
        this.permission = permission;
    }

    public Set<Permission> getPermission() {
        return permission;
    }
}

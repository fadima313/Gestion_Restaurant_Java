package com.app.enums;

public enum Role {
    Admin("Admin"),
    Chef("Chef"),
	Restaurateur("Restaurateur");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    public String getName() {
        return role;
    }
    
}


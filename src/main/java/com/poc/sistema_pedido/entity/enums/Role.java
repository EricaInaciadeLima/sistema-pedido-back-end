package com.poc.sistema_pedido.entity.enums;

public enum Role  {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String value;
    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
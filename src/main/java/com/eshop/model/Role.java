package com.eshop.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {

    BUYER(1, "ROLE_BUYER"),
    SELLER(2, "ROLE_SELLER"),
    ADMIN(3, "ROLE_ADMIN");

    private Integer id;
    private String roleName;


    public Integer getId(){
        return id;
    }


    public static Role getById(Integer id){
        for(Role role : values()){
            if (role.getId().equals(id)){
                return role;
            }
        }
        return null;
    }

}
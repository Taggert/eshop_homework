package com.eshop.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private String email;

    private String firstname;

    private String lastname;

    private Double balance;


}
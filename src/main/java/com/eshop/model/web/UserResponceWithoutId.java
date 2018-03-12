package com.eshop.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponceWithoutId {

    private String username;

    private String email;

    private String firstname;

    private String lastname;


}
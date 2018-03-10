package com.eshop.model.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponce {
    private String username;

    private String email;

    private String firstname;

    private String lastname;


}
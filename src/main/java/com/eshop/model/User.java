package com.eshop.model;

import com.eshop.model.web.UserResponce;
import com.eshop.model.web.UserResponceWithoutId;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String firstname;

    @Column(name = "LASTNAME", nullable = false, length = 50)
    private String lastname;

    @Column(name = "BALANCE")
    private Double balance;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    /*@JsonIgnore

    private UserResponce response = createResponse();

    @JsonIgnore
    private UserResponceWithoutId responseWithoutId = createResponseWithoutId();*/

    public UserResponce createResponse() {
        return UserResponce.builder()
                .id(id)
                .username(username)
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }

    public UserResponceWithoutId createResponseWithoutId() {

        return UserResponceWithoutId.builder()
                .username(username)
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }

}
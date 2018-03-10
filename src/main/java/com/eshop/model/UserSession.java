package com.eshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_SESSION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SESSION_ID", nullable = false, unique = true)
    private String sessionId;

    @OneToOne
    @JoinColumn(name = "USER", nullable = false)
    private User user;

    @Column(name = "IS_VALID")
    private Boolean isValid;


}
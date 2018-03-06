package com.eshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

}
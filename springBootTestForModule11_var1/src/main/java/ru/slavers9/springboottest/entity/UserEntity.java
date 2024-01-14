package ru.slavers9.springboottest.entity;

import jakarta.persistence.*;
/**
 * User entity for real DB
 * TODO want to do this kind of realization later
 */

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
}

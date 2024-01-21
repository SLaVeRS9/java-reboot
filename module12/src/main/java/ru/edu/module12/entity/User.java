package ru.edu.module12.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String name;

    @Column(name = "age")
    @Size(min = 1900,message = "Год рождения должен быть больше, чем 1900")
    private Integer age;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 100, message = "Пароль должен быть от 5 до 100 символов длиной")
    private String password;

    @Column(name = "user_role")
    private String role;
}

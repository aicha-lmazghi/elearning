package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
public class UserDto {

    String idUser;
    @NotBlank(message = "username is mandatory")
    String userName;
    @NotBlank(message = "password is mandatory")
    String password;
    @NotBlank(message = "first Name is mandatory")
    String firstName;
    @NotBlank(message = "last Name is mandatory")
    String lastName;
    @NotBlank(message = "email is mandatory")
    @Column(nullable = false, unique = true)
    String mail;
    @NotBlank(message = "number is mandatory")
    String fullNumber;
    String image;
    String dateNaiss;
    @NotBlank(message = "role is mandatory")
    String role;

}

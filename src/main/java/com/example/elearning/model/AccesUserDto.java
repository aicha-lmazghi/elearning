package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Getter
@Setter
public class AccesUserDto {
    @NotBlank(message = "username is mandatory")
    @Column(unique = true , nullable = false)
    String userName;
    @NotBlank(message = "password is mandatory")
    String password;
}

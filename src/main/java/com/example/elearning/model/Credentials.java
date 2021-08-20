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
public class Credentials {

    @NotBlank(message = "username is mandatory")
    String userName;
    @NotBlank(message = "password is mandatory")
    String password;
}

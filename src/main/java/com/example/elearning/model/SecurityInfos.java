package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor

@Entity
public class SecurityInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    SecretKey skey;


}

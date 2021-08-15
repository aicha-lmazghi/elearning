package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Document(collection = "Topics")
public class Topic {
    @Id
    String id;
    String name;
    String image;
    String description;
    String createdBy;
}

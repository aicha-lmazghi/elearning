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
@Document(collection = "SubTopics")
public class SubTopic {
    @Id
    String id;
    String name;
    String image;
    String description;
    String topic;
    String createdBy;
}

package com.example.elearning.model;

import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String image;
    String description;
    @ManyToOne
    User createdBy;
    @OneToMany(mappedBy = "topic")
    List<SubTopic> subTopicsList;
}

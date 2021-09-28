package com.example.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import java.util.List;


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
    String slug;
    String description;
    @ManyToOne
    User createdBy;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "topic",orphanRemoval = true)
    List<SubTopic> subTopicsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    public User getCreatedBy() {
        return createdBy;
    }
    @JsonSetter
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    @JsonIgnore
    public List<SubTopic> getSubTopicsList() {
        return subTopicsList;
    }
    @JsonSetter
    public void setSubTopicsList(List<SubTopic> subTopicsList) {
        this.subTopicsList = subTopicsList;
    }
}

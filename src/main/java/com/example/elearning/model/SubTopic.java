package com.example.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
;


import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@Entity
public class SubTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String image;
    String slug;
    String description;
    @ManyToOne
    User createdBy;
    @ManyToOne
    Topic topic;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subTopic",orphanRemoval = true)
    List<Ressource> ressources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
    public Topic getTopic() {
        return topic;
    }
    @JsonSetter
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    @JsonIgnore
    public List<Ressource> getRessources() {
        return ressources;
    }
    @JsonSetter
    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }
}

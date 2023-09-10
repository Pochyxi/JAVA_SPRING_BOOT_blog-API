package com.developez.Spring.boot.blog.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint ( columnNames = {"title"} )})
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    public Long id;

    @Column(
            name = "title",
            nullable = false
    )
    public String title;

    @Column(
            name = "description",
            nullable = false
    )
    public String description;

    @Column(
            name = "content",
            nullable = false
    )
    public String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();
}

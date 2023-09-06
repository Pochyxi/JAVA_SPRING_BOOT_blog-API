package com.developez.Spring.boot.blog.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}

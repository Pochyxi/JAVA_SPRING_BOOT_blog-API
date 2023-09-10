package com.developez.Spring.boot.blog.API.payload;

import lombok.Builder;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}

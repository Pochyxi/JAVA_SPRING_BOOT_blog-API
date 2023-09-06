package com.developez.Spring.boot.blog.API.service;

import com.developez.Spring.boot.blog.API.payload.PostDto;
import com.developez.Spring.boot.blog.API.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost( PostDto postDto );

    PostResponse getAllPosts( int pageNo, int pageSize);

    PostDto getPostById( Long id );

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);
}

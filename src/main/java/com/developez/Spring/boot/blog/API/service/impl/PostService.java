package com.developez.Spring.boot.blog.API.service.impl;

import com.developez.Spring.boot.blog.API.payload.PostDto;
import com.developez.Spring.boot.blog.API.payload.PostResponse;

public interface PostService {

    PostDto createPost( PostDto postDto );

    PostResponse getAllPosts( int pageNo, int pageSize, String sortBy, String sortDir );

    PostDto getPostById( Long id );

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);
}

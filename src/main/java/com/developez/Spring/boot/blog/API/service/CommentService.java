package com.developez.Spring.boot.blog.API.service;

import com.developez.Spring.boot.blog.API.entity.Comment;
import com.developez.Spring.boot.blog.API.payload.CommentDto;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto commentDto);
}

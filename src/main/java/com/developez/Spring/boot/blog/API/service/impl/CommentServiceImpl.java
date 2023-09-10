package com.developez.Spring.boot.blog.API.service.impl;

import com.developez.Spring.boot.blog.API.entity.Comment;
import com.developez.Spring.boot.blog.API.entity.Post;
import com.developez.Spring.boot.blog.API.exception.ResourceNotFoundException;
import com.developez.Spring.boot.blog.API.payload.CommentDto;
import com.developez.Spring.boot.blog.API.repository.CommentRepository;
import com.developez.Spring.boot.blog.API.repository.PostRepository;
import com.developez.Spring.boot.blog.API.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl( CommentRepository commentRepository,
                               PostRepository postRepository ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // Creare un commento
    @Override
    public CommentDto createComment( long postId, CommentDto commentDto ) {
        Comment comment = mapToEntity( commentDto );

        // Ricavare l'esistenza del post
        Post post = postRepository.findById( postId ).orElseThrow( () -> new ResourceNotFoundException( "Post", "id", postId ) );

        // Impostare il post al commento
        comment.setPost( post );

        // Salvare il commento
        Comment savedComment = commentRepository.save( comment );

        return mapToDto( savedComment);
    }

    private CommentDto mapToDto( Comment comment ) {
        return CommentDto.builder()
                .id( comment.getId() )
                .name( comment.getName() )
                .email( comment.getEmail() )
                .body( comment.getBody() )
                .build();
    }

    private Comment mapToEntity( CommentDto commentDto ) {
        return Comment.builder()
                .id( commentDto.getId() )
                .name( commentDto.getName() )
                .email( commentDto.getEmail() )
                .body( commentDto.getBody() )
                .build();
    }
}

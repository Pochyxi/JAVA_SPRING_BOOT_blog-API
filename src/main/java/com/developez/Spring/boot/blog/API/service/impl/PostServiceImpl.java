package com.developez.Spring.boot.blog.API.service.impl;

import com.developez.Spring.boot.blog.API.entity.Post;
import com.developez.Spring.boot.blog.API.exception.ResourceNotFoundException;
import com.developez.Spring.boot.blog.API.payload.PostDto;
import com.developez.Spring.boot.blog.API.payload.PostResponse;
import com.developez.Spring.boot.blog.API.repository.PostRepository;
import com.developez.Spring.boot.blog.API.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl( PostRepository postRepository ) {
        this.postRepository = postRepository;
    }

    // Creazione di un Post
    @Override
    public PostDto createPost( PostDto postDto ) {
        // Convertire il DTO in ENTITY
        Post post = mapToEntity( postDto );

        // Salvare l'ENTITY nel DB
        Post savedPost = postRepository.save( post );

        // Convertire l'ENTITY in DTO
        return mapToDTO( savedPost );
    }

    // Recupero di tutti i Post con paginazione e oggetto PostResponse
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {

        // creazione di un oggetto Pageable
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Post> postPageList = postRepository.findAll(pageable);

        // prendere il contenuto dal dall'oggetto Page
        List<Post> postList = postPageList.getContent();

        List<PostDto> content = postList.stream().map( this::mapToDTO ).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent( content );
        postResponse.setPageNo( postPageList.getNumber() );
        postResponse.setPageSize( postPageList.getSize() );
        postResponse.setTotalElements( postPageList.getTotalElements() );
        postResponse.setTotalPages( postPageList.getTotalPages() );
        postResponse.setLast( postPageList.isLast() );

        return postResponse;
    }

    // Recupero di un Post tramite ID
    @Override
    public PostDto getPostById( Long id ) {
        Post post = postRepository.findById( id ).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO( post );
    }

    // Aggiornamento di un Post tramite ID
    @Override
    public PostDto updatePost( PostDto postDto, Long id ) {
        // Recupero l'ENTITY dal DB tramite ID
        Post post = postRepository.findById( id ).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle( postDto.getTitle() );
        post.setDescription( postDto.getDescription() );
        post.setContent( postDto.getContent() );

        // Salvare l'ENTITY nel DB
        Post updatedPost = postRepository.save( post );
        return mapToDTO( updatedPost );
    }

    // Eliminazione di un Post tramite ID
    @Override
    public void deletePostById( Long id ) {
        // Recupero l'ENTITY dal DB tramite ID
        Post post = postRepository.findById( id ).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        // Eliminare l'ENTITY dal DB
        postRepository.delete( post );
    }

    // Convertire l'ENTITY in DTO
    private PostDto mapToDTO( Post post ) {
        PostDto postDto = new PostDto();
        postDto.setId( post.getId() );
        postDto.setTitle( post.getTitle() );
        postDto.setDescription( post.getDescription() );
        postDto.setContent( post.getContent() );
        return postDto;
    }

    // Convertire il DTO in ENTITY
    private Post mapToEntity( PostDto postDto ) {
        Post post = new Post();
        post.setId( postDto.getId() );
        post.setTitle( postDto.getTitle() );
        post.setDescription( postDto.getDescription() );
        post.setContent( postDto.getContent() );
        return post;
    }
}
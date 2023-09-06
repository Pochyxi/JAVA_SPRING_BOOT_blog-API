package com.developez.Spring.boot.blog.API.controller;

import com.developez.Spring.boot.blog.API.payload.PostDto;
import com.developez.Spring.boot.blog.API.payload.PostResponse;
import com.developez.Spring.boot.blog.API.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    // Creazione di un Post
    @PostMapping
    public ResponseEntity<PostDto> createPost( @RequestBody PostDto postDto ){
        return new ResponseEntity( postService.createPost( postDto ), HttpStatus.CREATED );
    }

    // Recupero di tutti i Post con paginazione e oggetto PostResponse
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ){
        return new ResponseEntity( postService.getAllPosts(pageNo, pageSize), HttpStatus.OK );
    }

    // Recupero di un Post tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById( @PathVariable("id") Long id ){
        return new ResponseEntity( postService.getPostById( id ), HttpStatus.OK );
    }

    // Aggiornamento di un Post tramite ID
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost( @RequestBody PostDto postDto, @PathVariable("id") Long id ){
        return new ResponseEntity( postService.updatePost( postDto, id ), HttpStatus.OK );
    }

    // Eliminazione di un Post tramite ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById( @PathVariable("id") Long id ){
        postService.deletePostById( id );
        return new ResponseEntity( "Post eliminato con successo.", HttpStatus.OK );
    }
}
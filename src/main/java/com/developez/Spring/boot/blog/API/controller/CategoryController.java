package com.developez.Spring.boot.blog.API.controller;

import com.developez.Spring.boot.blog.API.payload.CategoryDto;
import com.developez.Spring.boot.blog.API.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    // Costruzione della addCategory REST API
    @PostMapping
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto ) {
        CategoryDto response = categoryService.addCategory( categoryDto );

        return new ResponseEntity<>( response, HttpStatus.CREATED );
    }
}

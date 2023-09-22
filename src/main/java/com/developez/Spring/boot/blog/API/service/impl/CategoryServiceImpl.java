package com.developez.Spring.boot.blog.API.service.impl;

import com.developez.Spring.boot.blog.API.entity.Category;
import com.developez.Spring.boot.blog.API.payload.CategoryDto;
import com.developez.Spring.boot.blog.API.repository.CategoryRepository;
import com.developez.Spring.boot.blog.API.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl( CategoryRepository categoryRepository, ModelMapper modelMapper ) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory( CategoryDto categoryDto ) {

        Category category = modelMapper.map( categoryDto, Category.class );
        Category savedCategory = categoryRepository.save( category );

        return modelMapper.map( savedCategory, CategoryDto.class );
    }
}

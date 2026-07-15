package com.movieflix.service;

import com.movieflix.dto.response.CategoryResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO saveCategory(Category category){
        Category newCategory = categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }

    public List<CategoryResponseDTO> findAll(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toResponse).toList();
    }

    public CategoryResponseDTO findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        return CategoryMapper.toResponse(category);
    }

    public void deleteById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        categoryRepository.deleteById(category.getId());
    }

}

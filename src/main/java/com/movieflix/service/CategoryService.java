package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public List<Category> findAllByCategory(List<Long> categoryId){
        return categoryRepository.findAllById(categoryId);
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);

    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

}

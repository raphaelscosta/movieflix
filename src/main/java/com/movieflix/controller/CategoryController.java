package com.movieflix.controller;


import com.movieflix.dto.request.CategoryRequestDTO;
import com.movieflix.dto.response.CategoryResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService= categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody CategoryRequestDTO categoryRequestDTO){
        Category savedCategory = categoryService.save(CategoryMapper.toEntity(categoryRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toResponse(savedCategory));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        return ResponseEntity.ok(categoryService.findAll()
                .stream().map(CategoryMapper::toResponse)
                .toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
        return categoryService.findById(id).map(category ->
                ResponseEntity.ok(CategoryMapper.toResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        Optional<Category> categorySearched = categoryService.findById(id);
        if(categorySearched.isPresent()){
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

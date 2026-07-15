package com.movieflix.mapper;

import com.movieflix.dto.request.CategoryRequestDTO;
import com.movieflix.dto.response.CategoryResponseDTO;
import com.movieflix.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.name());
        return category;
    }

    public static CategoryResponseDTO toResponse(Category category){
        CategoryResponseDTO categoryResponse = new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
        return categoryResponse;
    }


}

package com.movieflix.mapper;

import com.movieflix.dto.request.MovieRequestDTO;
import com.movieflix.dto.response.CategoryResponseDTO;
import com.movieflix.dto.response.MovieResponseDTO;
import com.movieflix.dto.response.StreamingResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;

import java.util.List;

public class MovieMapper {

    public static Movie toEntity(MovieRequestDTO movieRequestDTO){

        List<Streaming> streamings = movieRequestDTO.streamings().stream().map(
                streamingId -> {
                    Streaming streaming = new Streaming();
                    streaming.setId(streamingId);
                    return streaming;
                }).toList();

        List<Category> categories = movieRequestDTO.categories().stream().map(
                categoryId -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }).toList();



        Movie movie = new Movie();
        movie.setTitle(movieRequestDTO.title());
        movie.setDescription(movieRequestDTO.description());
        movie.setReleaseDate(movieRequestDTO.releaseDate());
        movie.setRating(movieRequestDTO.rating());
        movie.setCategories(categories);
        movie.setStreamings(streamings);

        return movie;
    }

    public static MovieResponseDTO toResponse(Movie movie){
        List<CategoryResponseDTO> categoryResponseDTOS = movie.getCategories().stream().map(
                category -> new CategoryResponseDTO(category.getId(), category.getName())
        ).toList();

        List<StreamingResponseDTO> streamingResponseDTOS = movie.getStreamings().stream().map(
                streaming -> new StreamingResponseDTO(streaming.getId(), streaming.getName())
                ).toList();

       return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getRating(),
                categoryResponseDTOS,
                streamingResponseDTOS
        );
    }


}

package com.movieflix.controller;

import com.movieflix.dto.request.MovieRequestDTO;
import com.movieflix.dto.response.MovieResponseDTO;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> save(@RequestBody MovieRequestDTO movieRequestDTO){
        Movie movie = movieService.save(MovieMapper.toEntity(movieRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toResponse(movie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll(){
        return ResponseEntity.ok(movieService.findAll().stream().map(MovieMapper::toResponse).toList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDTO>> findByCategory(@RequestParam Long categoryId){
        return ResponseEntity.ok(movieService.findByCategory(categoryId).stream().map(MovieMapper::toResponse).toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id){
        return movieService.findById(id).map(movie -> ResponseEntity.ok(MovieMapper.toResponse(movie))).orElse(
                ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO){
        return movieService.update(id,MovieMapper.toEntity(movieRequestDTO))
                .map(movie -> ResponseEntity.ok(MovieMapper.toResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        Optional<Movie> movieSearched = movieService.findById(id);
        if(movieSearched.isPresent()){
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }





}

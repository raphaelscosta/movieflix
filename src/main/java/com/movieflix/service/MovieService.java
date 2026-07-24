package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService,
                        StreamingService streamingService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public Movie save(Movie newMovie) {
        List<Long> categoryIds = newMovie.getCategories().stream().map(Category::getId).toList();
        newMovie.setCategories(this.findAllCategories(categoryIds));

        List<Long> streamingIds = newMovie.getStreamings().stream().map(Streaming::getId).toList();
        newMovie.setStreamings(this.findAllStreamings(streamingIds));
        return movieRepository.save(newMovie);


    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteById(Long id) {
            movieRepository.deleteById(id);
    }

    public Optional<Movie> update(Long id, Movie movie){
        Optional<Movie> searchMovie = movieRepository.findById(id);

        if(searchMovie.isPresent()) {

            Movie movieToUpdate = searchMovie.get();

            List<Category> newCategories = this.findAllCategories(movie.getCategories().stream().map(
                    Category::getId).toList());
            List<Streaming> newStreamings = this.findAllStreamings(movie.getStreamings().stream().map(
                    Streaming::getId).toList());

            movieToUpdate.setTitle(movie.getTitle());
            movieToUpdate.setDescription(movie.getDescription());
            movieToUpdate.setRating(movie.getRating());
            movieToUpdate.setReleaseDate(movie.getReleaseDate());
            movieToUpdate.getCategories().clear();
            movieToUpdate.getCategories().addAll(newCategories);
            movieToUpdate.getStreamings().clear();
            movieToUpdate.getStreamings().addAll(newStreamings);

            Movie savedMovie = movieRepository.save(movieToUpdate);
            return Optional.of(savedMovie);
        }

        return Optional.empty();
    }

    public List<Movie> findByCategory(Long id) {
        return movieRepository.findAllMoviesByCategoriesId(id);
    }

    private List<Category> findAllCategories(List<Long> categoryIds) {
        return categoryService.findAllByCategory(categoryIds);
    }

    private List<Streaming> findAllStreamings(List<Long> streamingIds) {
        return streamingService.findAllById(streamingIds);
    }



}

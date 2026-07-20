package com.movieflix.controller;

import com.movieflix.entity.Movie;
import com.movieflix.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    public final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }


}

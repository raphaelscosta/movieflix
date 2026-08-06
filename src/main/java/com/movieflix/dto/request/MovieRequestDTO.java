package com.movieflix.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.entity.Movie;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record MovieRequestDTO(
                              @NotBlank(message = "O título é obrigatório")
                              String title,
                              String description,
                              @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
                              LocalDate releaseDate,
                              double rating,
                              List<Long> categories,
                              List<Long> streamings){


}

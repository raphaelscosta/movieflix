package com.movieflix.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record MovieResponseDTO(Long id,
                               String title,
                               String description,

                               @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
                               LocalDate releaseDate,
                               Double rating,
                               List<CategoryResponseDTO> categories,
                               List<StreamingResponseDTO> streamings) {
}

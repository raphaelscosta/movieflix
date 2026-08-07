package com.movieflix.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank(message = "O nome da categoria é obrigatório") String name) {
}

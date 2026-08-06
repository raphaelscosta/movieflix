package com.movieflix.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StreamingRequestDTO(@NotBlank(message = "O nome do streaming é obrigatório") String name) {
}

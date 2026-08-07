package com.movieflix.dto.request;


import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O email é obrigatório")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String password) {

}

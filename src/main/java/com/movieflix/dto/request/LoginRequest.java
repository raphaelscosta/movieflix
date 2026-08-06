package com.movieflix.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "Digite o email") String email,
                           @NotBlank(message = "Digite a senha") String password){
}

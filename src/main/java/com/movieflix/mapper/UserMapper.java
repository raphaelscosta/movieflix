package com.movieflix.mapper;

import com.movieflix.dto.request.UserRequestDTO;
import com.movieflix.dto.response.UserResponseDTO;
import com.movieflix.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setPassword(userRequestDTO.password());

        return user;
    }

    public static UserResponseDTO toResponse(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }
}

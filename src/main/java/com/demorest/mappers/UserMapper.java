package com.demorest.mappers;

import com.demorest.dtos.UserDTO;
import com.demorest.entities.User;

import java.util.UUID;

public class UserMapper {

    public User buildEntity(UserDTO dto){

        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());

        return newUser;
    }

    public UserDTO buildDTO(User user){

        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setName(user.getName());
        newUserDTO.setEmail(user.getEmail());

        return newUserDTO;
    }
}

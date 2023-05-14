package com.gmartino.trees.service;

import com.gmartino.trees.dto.UserDTO;
import com.gmartino.trees.entity.User;

public class UserFactory {

    public static UserDTO userToUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
    }

    public static User userDTOToUser(UserDTO dto) {
        return new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
    }
}

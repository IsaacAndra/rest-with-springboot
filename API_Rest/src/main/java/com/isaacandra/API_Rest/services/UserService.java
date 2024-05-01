package com.isaacandra.API_Rest.services;

import com.isaacandra.API_Rest.domain.user.CreateUserDTO;
import com.isaacandra.API_Rest.domain.user.User;
import com.isaacandra.API_Rest.domain.user.UserDTO;
import com.isaacandra.API_Rest.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserDTO(user);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(CreateUserDTO data) {
        User user = mapToUser(data);
        User savedUser = userRepository.save(user);
        return mapToUserDTO(savedUser);
    }

    public UserDTO updateUser(Long id, CreateUserDTO data) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(data.userName());
        user.setAddress(data.address());
        user.setGender(data.gender());
        User updatedUser = userRepository.save(user);
        return mapToUserDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private User mapToUser(CreateUserDTO data) {
        return new User(null, data.userName(), data.address(), data.gender());
    }

    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUserName(), user.getAddress(), user.getGender());
    }
}

package com.isaacandra.API_Rest.services;

import com.isaacandra.API_Rest.domain.user.CreateUserDTO;
import com.isaacandra.API_Rest.domain.user.User;
import com.isaacandra.API_Rest.domain.user.UserDTO;
import com.isaacandra.API_Rest.domain.user.UserRepository;
import com.isaacandra.API_Rest.exceptions.ConstraintViolationException;
import com.isaacandra.API_Rest.exceptions.UserNotFoundException;
import com.isaacandra.API_Rest.infra.ApplicationExceptionHandler;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ApplicationExceptionHandler exceptionHandler;

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " was Not Found"));
        return mapToUserDTO(user);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException();
        }
        return users.stream().map(this::mapToUserDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(CreateUserDTO data) {
        User user = mapToUser(data);
        if (data.userName() == null|| data.address() == null || data.gender() == null){
            throw new ConstraintViolationException("Username, address, and gender cannot be null");
        }
        User savedUser = userRepository.save(user);



        return mapToUserDTO(savedUser);
    }

    public UserDTO updateUser(Long id, CreateUserDTO data) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " was Not Found"));
        if (data.userName() == null|| data.address() == null || data.gender() == null){
            throw new ConstraintViolationException("Username, address, and gender cannot be null");
        }

        user.setUserName(data.userName());
        user.setAddress(data.address());
        user.setGender(data.gender());

        User updatedUser = userRepository.save(user);

        return mapToUserDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with " + id + " was Not Found"));
        userRepository.delete(user);
    }

    private User mapToUser(CreateUserDTO data) {
        return new User(null, data.userName(), data.address(), data.gender());
    }

    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUserName(), user.getAddress(), user.getGender());
    }
}

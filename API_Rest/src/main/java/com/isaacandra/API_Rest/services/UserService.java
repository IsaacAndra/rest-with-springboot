package com.isaacandra.API_Rest.services;

import com.isaacandra.API_Rest.domain.user.*;
import com.isaacandra.API_Rest.exceptions.ConstraintViolationException;
import com.isaacandra.API_Rest.exceptions.UserNotFoundException;
import com.isaacandra.API_Rest.infra.ApplicationExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ApplicationExceptionHandler exceptionHandler;

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " was Not Found"));
        log.info("Usuario por Id pelo endpoint /users");
        return mapToUserDTO(user);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException();
        }
        log.info("Todos usuarios pelo endpoint /users");
        return users.stream().map(this::mapToUserDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(CreateUserDTO data) {

        User user = new User(data);
        if (data.userName() == null|| data.address() == null || data.gender() == null){
            throw new ConstraintViolationException("Username, address, and gender cannot be null");
        }

        User savedUser = userRepository.save(user);
        log.info("Criando usuario pelo endpoint /users");

        return mapToUserDTO(savedUser);
    }

    public UserDTO updateUser(Long id, EditUserDTO data) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " was Not Found"));
        if (data.userName() == null && data.address() == null && data.gender() == null){
            throw new ConstraintViolationException("Username, address, and gender cannot be null");

        }
        user.updatedUser(data);
        userRepository.save(user);
        log.info("Atualizando o usuario pelo endpoint /users");
        return mapToUserDTO(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with Id " + id + " was Not Found"));
        userRepository.delete(user);
        log.info("Deletando usuario por ID pelo endpoint /users");
    }


    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUserName(), user.getAddress(), user.getGender());
    }
}

package com.isaacandra.API_Rest.services;

import com.isaacandra.API_Rest.domain.user.User;
import com.isaacandra.API_Rest.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void createUser() {
        User user = new User(
                1L,
                "Isaac",
                "Federal District - Brazil",
                "Male");
        User savedUser = userRepository.save(user);

        when(userRepository.save(savedUser)).thenReturn(user);
        verify(userRepository, times(1)).save(any());
        user.setUserName(new String("Andrade"));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}
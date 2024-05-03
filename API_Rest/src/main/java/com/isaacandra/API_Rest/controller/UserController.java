package com.isaacandra.API_Rest.controller;

import com.isaacandra.API_Rest.domain.user.CreateUserDTO;
import com.isaacandra.API_Rest.domain.user.EditUserDTO;
import com.isaacandra.API_Rest.domain.user.UserDTO;
import com.isaacandra.API_Rest.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for Manager People")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para obter todos os usuários
    @GetMapping
    @Operation(summary = "Finds all Users", description = "Finds all Users",
    tags = {"Users"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Endpoint para obter um usuário por ID
    @GetMapping("/{id}")
    @Operation(summary = "Find a User", description = "Find a User",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    @Operation(summary = "Adds a new User", description = "Adds a new User",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        UserDTO newUser = userService.createUser(createUserDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um usuário existente
    @PutMapping("/{id}")
    @Operation(summary = "Update a User", description = "Update a User",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid EditUserDTO data) {
        UserDTO updatedUser = userService.updateUser(id, data);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint para excluir um usuário por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User", description = "Delete a User",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

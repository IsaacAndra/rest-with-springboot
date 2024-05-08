package com.isaacandra.API_Rest.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        String userName,
        String address,
        String gender
) {}

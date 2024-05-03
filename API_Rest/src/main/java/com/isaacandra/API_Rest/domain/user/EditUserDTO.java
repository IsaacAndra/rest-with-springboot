package com.isaacandra.API_Rest.domain.user;

import jakarta.validation.constraints.NotBlank;

public record EditUserDTO(
        @NotBlank
        String userName,
        @NotBlank
        String address,
        @NotBlank
        String gender
) {}

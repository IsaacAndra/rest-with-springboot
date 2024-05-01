package com.isaacandra.API_Rest.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(@NotBlank String userName, @NotBlank String address,@NotBlank String gender) {
}

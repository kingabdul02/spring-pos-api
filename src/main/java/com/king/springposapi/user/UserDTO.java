package com.king.springposapi.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String email,
        String firstName,
        String lastName,
        LocalDateTime lastLogin
) {
}

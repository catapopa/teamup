package com.project.teamup.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String jwtToken;
}

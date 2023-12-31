package com.lolmaxlevel.backend_j.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtRequest(
        @JsonProperty("username") String username,
        @JsonProperty("password") String password
) {
}

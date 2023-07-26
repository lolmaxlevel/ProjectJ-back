package com.lolmaxlevel.backend_j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadResponse {
    private String message;
    private String name;
    private String description;
    private Long id;

    public UploadResponse(String message) {
        this.message = message;
    }
}


package com.lolmaxlevel.backend_j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class SchoolAddResponse {
    private String message;
    private String name;
    private String link;
    private Long id;
    private String grade;
    private String type;

    public SchoolAddResponse(String message) {
        this.message = message;
    }
}


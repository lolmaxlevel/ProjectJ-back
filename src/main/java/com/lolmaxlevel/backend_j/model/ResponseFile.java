package com.lolmaxlevel.backend_j.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseFile {
    private Long id;
    private String name;
    private String url;
}
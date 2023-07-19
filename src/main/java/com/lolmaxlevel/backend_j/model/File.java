package com.lolmaxlevel.backend_j.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

//    private String type;

    private String uri;

    public File(String fileName, String location, String description) {
        this.name = fileName;
        this.uri = location;
        this.description = description;
    }
    public File(Long id,String fileName, String description) {
        this.id = id;
        this.name = fileName;
        this.description = description;
    }
}

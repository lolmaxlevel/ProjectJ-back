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

//    private String type;

    private String uri;

    public File(String fileName, String location) {
        this.name = fileName;
        this.uri = location;
    }

}

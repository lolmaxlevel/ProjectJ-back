package com.lolmaxlevel.backend_j.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "school")
public class SchoolMaterial {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String link;

    private String grade;

    private String type;

    public SchoolMaterial(String name, String link, String grade, String type) {
        this.name = name;
        this.link = link;
        this.grade = grade;
        this.type = type;
    }
}

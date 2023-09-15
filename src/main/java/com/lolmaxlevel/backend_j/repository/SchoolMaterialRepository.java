package com.lolmaxlevel.backend_j.repository;

import com.lolmaxlevel.backend_j.model.SchoolMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SchoolMaterialRepository extends JpaRepository<SchoolMaterial, Long> {
    List<SchoolMaterial> findAllByGrade(String grade);
}

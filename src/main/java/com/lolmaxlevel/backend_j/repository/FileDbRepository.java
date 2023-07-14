package com.lolmaxlevel.backend_j.repository;

import com.lolmaxlevel.backend_j.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbRepository extends JpaRepository<File, Long> {}

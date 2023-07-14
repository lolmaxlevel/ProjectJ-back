package com.lolmaxlevel.backend_j.repository;

import org.springframework.core.io.FileSystemResource;

public interface FileSystemRepository {
    String save(byte[] content, String fileName) throws Exception;

    FileSystemResource findInFileSystem(String location);
}

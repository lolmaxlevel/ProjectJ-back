package com.lolmaxlevel.backend_j.service;

import com.lolmaxlevel.backend_j.model.File;

import com.lolmaxlevel.backend_j.model.ResponseFile;
import com.lolmaxlevel.backend_j.repository.FileDbRepository;
import com.lolmaxlevel.backend_j.repository.FileSystemRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileLocationService {
    private final FileSystemRepositoryImpl fileSystemRepositoryImpl;
    private final FileDbRepository fileDbRepository;

    @Autowired
    public FileLocationService(FileSystemRepositoryImpl fileSystemRepositoryImpl, FileDbRepository fileDbRepository) {
        this.fileSystemRepositoryImpl = fileSystemRepositoryImpl;
        this.fileDbRepository = fileDbRepository;
    }

    public void save(byte[] bytes, String fileName) throws Exception {
        String location = fileSystemRepositoryImpl.save(bytes, fileName);

        fileDbRepository.save(new File(fileName, location));
    }

    public FileSystemResource find(Long fileId) {
        File file = fileDbRepository.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepositoryImpl.findInFileSystem(file.getUri());
    }

    public ResponseFile[] getAllFiles(){
        return fileDbRepository.findAll().stream().map(file -> new ResponseFile(file.getId(), file.getName(), file.getUri())).toArray(ResponseFile[]::new);
    }
}
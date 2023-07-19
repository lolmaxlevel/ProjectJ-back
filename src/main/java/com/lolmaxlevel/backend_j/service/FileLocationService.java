package com.lolmaxlevel.backend_j.service;

import com.lolmaxlevel.backend_j.model.File;

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

    public File save(byte[] bytes, String fileName, String description) throws Exception {
        String location = fileSystemRepositoryImpl.save(bytes, fileName);

        return fileDbRepository.save(new File(fileName, location, description));
    }

    public FileSystemResource find(Long fileId) {
        File file = fileDbRepository.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepositoryImpl.findInFileSystem(file.getUri());
    }

    public File[] getAllFiles(){
        return fileDbRepository.findAll().stream().map(file -> new File(file.getId(), file.getName(), file.getDescription())).toArray(File[]::new);
    }
    public String deleteFile(Long id){
        fileDbRepository.deleteById(id);
        return "Deleted";
    }
    public String updateFile(Long id, String name, String description){
        File file = fileDbRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        file.setName(name);
        file.setDescription(description);
        fileDbRepository.save(file);
        return "Updated";
    }
}
package com.lolmaxlevel.backend_j.controller;

import com.lolmaxlevel.backend_j.model.ResponseFile;
import com.lolmaxlevel.backend_j.model.ResponseMessage;
import com.lolmaxlevel.backend_j.service.FileLocationService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
class FileController {

    private final FileLocationService fileLocationService;

    public FileController(FileLocationService fileLocationService) {
        this.fileLocationService = fileLocationService;
    }

    @PostMapping("/file")
    ResponseMessage uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            fileLocationService.save(file.getBytes(), file.getOriginalFilename());
            return new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return new ResponseMessage("Error: " + e.getMessage());
        }

    }

    @GetMapping(value = "/files/{fileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    FileSystemResource downloadFile(@PathVariable Long fileId){
        return fileLocationService.find(fileId);
    }

    @GetMapping(value = "/all-files/")
    ResponseFile[] getAllFiles(){
        return fileLocationService.getAllFiles();
    }

}
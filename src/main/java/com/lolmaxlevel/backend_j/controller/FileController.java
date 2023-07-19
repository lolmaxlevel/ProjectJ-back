package com.lolmaxlevel.backend_j.controller;

import com.lolmaxlevel.backend_j.model.File;
import com.lolmaxlevel.backend_j.model.ResponseMessage;
import com.lolmaxlevel.backend_j.model.UploadResponse;
import com.lolmaxlevel.backend_j.service.FileLocationService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
class FileController {

    private final FileLocationService fileLocationService;

    public FileController(FileLocationService fileLocationService) {
        this.fileLocationService = fileLocationService;
    }

    @PostMapping("/upload")
    UploadResponse uploadImage(@RequestParam("file") MultipartFile file,
                                @RequestParam("description") String description,
                                @RequestParam("name") String name) {
        try {
            File new_file = fileLocationService.save(file.getBytes(), name, description);
            return new UploadResponse("Uploaded the file successfully!",
                    new_file.getName(), new_file.getDescription(), new_file.getId());
        } catch (Exception e) {
            return new UploadResponse("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/files/{fileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    FileSystemResource downloadFile(@PathVariable Long fileId){
        return fileLocationService.find(fileId);
    }

    @GetMapping(value = "/all-files")
    File[] getAllFiles(){
        return fileLocationService.getAllFiles();
    }
    @PostMapping(value = "/delete-file")
    ResponseMessage deleteFile(@RequestParam Long id){
        return new ResponseMessage(fileLocationService.deleteFile(id));
    }
    @PostMapping(value = "/update-file")
    ResponseMessage updateFile(@RequestParam Long id, @RequestParam String name, @RequestParam String description){
        return new ResponseMessage(fileLocationService.updateFile(id, name, description));
    }
}
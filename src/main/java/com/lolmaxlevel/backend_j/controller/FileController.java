package com.lolmaxlevel.backend_j.controller;

import com.lolmaxlevel.backend_j.model.File;
import com.lolmaxlevel.backend_j.dto.ResponseMessage;
import com.lolmaxlevel.backend_j.dto.UploadResponse;
import com.lolmaxlevel.backend_j.service.FileLocationService;
import com.lolmaxlevel.backend_j.utils.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/files")
class FileController {

    private final FileLocationService fileLocationService;

    public FileController(FileLocationService fileLocationService) {
        this.fileLocationService = fileLocationService;
    }

    @PostMapping("/upload")
    UploadResponse uploadImage(@RequestParam("file") MultipartFile file,
                                @RequestParam("description") String description,
                                @RequestParam("name") String name) {
        log.info("Upload request: {}", file.getOriginalFilename());
        try {
            File new_file = fileLocationService.save(file.getBytes(), file.getOriginalFilename(), name, description);
            return new UploadResponse("Uploaded the file successfully!",
                    new_file.getName(), new_file.getDescription(), new_file.getId());
        } catch (Exception e) {
            return new UploadResponse("Error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/file/{fileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    FileSystemResource downloadFile(@PathVariable Long fileId, HttpServletResponse response){
        log.info("Download request: {}", fileId);
        String fileName =
                Objects.requireNonNull(fileLocationService.find(fileId).getFilename()).split("-",2)[1];
        fileName = StringUtils.convertCyrilic(fileName.toLowerCase());
        response.addHeader("Content-Disposition", "attachment; filename="+fileName);
        return fileLocationService.find(fileId);
    }

    @GetMapping(value = "/all-files")
    File[] getAllFiles(){
        log.info("Get all files request");
        return fileLocationService.getAllFiles();
    }
    @PostMapping(value = "/delete-file")
    ResponseMessage deleteFile(@RequestParam Long id){
        log.info("Delete request: {}", id);
        return new ResponseMessage(fileLocationService.deleteFile(id));
    }
    @PutMapping(value = "/update-file")
    ResponseMessage updateFile(@RequestParam Long id, @RequestParam String name, @RequestParam String description){
        log.info("Update request: {}", id);
        return new ResponseMessage(fileLocationService.updateFile(id, name, description));
    }

}
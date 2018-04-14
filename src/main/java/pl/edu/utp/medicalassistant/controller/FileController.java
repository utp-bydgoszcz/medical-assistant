package pl.edu.utp.medicalassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.utp.medicalassistant.service.FileService;

import java.io.IOException;

@RestController
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestBody MultipartFile multipartFile,@RequestParam String id) throws IOException {
        return fileService.uploadFile(multipartFile,id);}

    @GetMapping(value = "/read/{id}",produces = "image/jpg")
    public ResponseEntity<byte[]> readFile(@PathVariable String id) throws IOException { return
            new ResponseEntity<>(fileService.readFile(id),HttpStatus.OK);
    }

}

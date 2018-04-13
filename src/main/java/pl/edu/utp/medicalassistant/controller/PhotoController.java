package pl.edu.utp.medicalassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.utp.medicalassistant.service.PhotoService;

@RestController
@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin", "*"})
@RequestMapping("/api/v1/file")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity uploadFile(@PathVariable String id, @RequestParam("files[]") MultipartFile[] files) { return photoService.uploadFile(id, files);}

    @GetMapping("/read/{id}")
    public HttpEntity<byte[]> readFile(@PathVariable String id) { return photoService.readFile(id); }

    @GetMapping("/find/{id}")
    public ResponseEntity findFiles(@PathVariable String id ) { return photoService.findFiles(id); }
}
package pl.edu.utp.medicalassistant.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ResponseEntity uploadFile(MultipartFile[] files);
    HttpEntity<byte[]> readFile(String id);

}

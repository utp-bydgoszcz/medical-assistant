package pl.edu.utp.medicalassistant.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    ResponseEntity uploadFile(MultipartFile multipartFile,String usernameId) throws IOException;
    byte[] readFile(String id) throws IOException;

}

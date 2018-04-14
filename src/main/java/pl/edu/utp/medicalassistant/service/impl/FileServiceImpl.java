package pl.edu.utp.medicalassistant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.utp.medicalassistant.model.Photo;
import pl.edu.utp.medicalassistant.repository.PhotoRepository;
import pl.edu.utp.medicalassistant.service.FileService;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public ResponseEntity uploadFile(MultipartFile multipartFile,String usernameId) throws IOException {

        photoRepository.save(new Photo( multipartFile.getBytes(),usernameId));

        return new ResponseEntity("Zdjecie wyslane", HttpStatus.OK);

    }

    @Override
    public byte[] readFile(String id) throws IOException {
        return photoRepository.findByUsernameId(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pliku ")).getFile();

    }
}

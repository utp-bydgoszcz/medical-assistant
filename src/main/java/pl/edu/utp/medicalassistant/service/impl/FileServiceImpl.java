package pl.edu.utp.medicalassistant.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.utp.medicalassistant.exception.FileException;
import pl.edu.utp.medicalassistant.service.FileService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j
public class FileServiceImpl implements FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public ResponseEntity uploadFile(String id, MultipartFile[] files) {
        DBObject metaData = new BasicDBObject();
        metaData.put("id", id);

        InputStream inputStream = null;

        for (int x = 0; x < files.length; x++) {
            try {
                inputStream = new BufferedInputStream(files[x].getInputStream());
                String fileId = gridFsTemplate.store(inputStream, files[x].getOriginalFilename(), files[x].getContentType(), metaData).getId().toString();
                log.info("Dodano nowy plik id=[" + fileId + "].");
            } catch (Exception e) {
                log.error("Błąd przy dodawaniu pliku: " + e.toString());
                throw new FileException("Błąd przy dodawaniu plików.");
            }
        }
        if (files.length == 1)
            return new ResponseEntity("Plik dodano prawidłowo", HttpStatus.OK);
        else
            return new ResponseEntity("Pliki dodano prawidłowo", HttpStatus.OK);

    }

    @Override
    public HttpEntity<byte[]> readFile(String id) {

        List<GridFSDBFile> result = gridFsTemplate.find(new Query().addCriteria(Criteria.where("_id").is(id)));

        for (GridFSDBFile file : result) {
            try {
                byte[] document = toByteArray(file.getInputStream());
                HttpHeaders header = new HttpHeaders();

                String[] parts = file.getContentType().split("/");
                String mediaType = parts[0];
                String mediaSubType = parts[1];

                header.setContentType(new MediaType(mediaType, mediaSubType));
//                header.set("Content-Disposition", "inline; filename="+file.getFilename());
                header.setContentDispositionFormData(file.getFilename(), file.getFilename());
                header.set("Access-Control-Expose-Headers", "Content-Disposition");
                header.setContentLength(document.length);
                return new HttpEntity<>(document, header);
            } catch (IOException e) {
                log.error("Błąd przy odczycie pliku: " + e.toString());
                throw new FileException("Błąd przy odczycie plików.");
            }
        }
        throw new FileException("Nie odnaleziono plików.");
    }

    @Override
    public ResponseEntity findFiles(String id) {

        List<GridFSDBFile> result = gridFsTemplate.find(new Query(Criteria.where("metadata.id").is(id)));
        List<Map<String, String>> filesList = new ArrayList<>();

        for (GridFSDBFile file : result) {

            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(file.getId()));
            map.put("name", file.getFilename());

            filesList.add(map);
        }
        return new ResponseEntity(filesList, HttpStatus.OK);
    }

    private byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int l;
        byte[] data = new byte[1024];
        while ((l = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, l);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}

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
public class FileServiceImpl implements FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public ResponseEntity uploadFile(MultipartFile[] files) {
        DBObject metaData = new BasicDBObject();
        List<String> listId = new ArrayList<>();

        InputStream inputStream = null;

        for (int x = 0; x < files.length; x++) {



        }
        return new ResponseEntity(listId, HttpStatus.OK);

    }

    @Override
    public HttpEntity<byte[]> readFile(String id) {


        throw new FileException("Nie odnaleziono plików.");
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

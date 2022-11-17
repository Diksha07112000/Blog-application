package com.diksha.blog.demo.services.impl;

import com.diksha.blog.demo.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file se data ko nikal k path me store krna h

        //File name
        String fileName = file.getOriginalFilename();

        //generate random name for the file
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

        //Fullpath
        String filePath = path + File.separator + fileName1;


        //create folder if not created
        File f= new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath
        ));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        //fullpath
        String fullPath = path + File.separator + fileName;

        InputStream is =new FileInputStream(fullPath);
        return is;
    }
}

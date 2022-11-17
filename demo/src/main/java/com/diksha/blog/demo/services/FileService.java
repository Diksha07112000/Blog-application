package com.diksha.blog.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    //upload image
    String uploadImage(String path, MultipartFile file) throws IOException;

    //serve/download image
    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}

//path is kha upload krna h
// file is kha data milega

package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface CarService {

    boolean hasCSVFormat(final MultipartFile file);

    void saveCars(final InputStream inputStream);
}

package com.ciurezu.gheorghe.dragos.greenlight.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadPhoto(MultipartFile photo);

}

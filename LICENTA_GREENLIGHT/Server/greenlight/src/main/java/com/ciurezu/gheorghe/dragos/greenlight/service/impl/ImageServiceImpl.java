package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.service.ImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
@Service

@Slf4j
public class ImageServiceImpl implements ImageService {
    private final Credentials credentials;

    {
        try {
            credentials = GoogleCredentials
                    .fromStream(new FileInputStream("src/main/resources/static/greenlight2.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
            .setProjectId("greenlightcloud-385612").build().getService();

    @Override
    public String uploadPhoto(MultipartFile photo) {
        try {

            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder("greenlight_photos2", photo.getOriginalFilename()).setContentType("image/jpeg").build(), //get original file name
                    photo.getBytes(),// Set file
                    Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
            );
            return blobInfo.getMediaLink(); // Return file url
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}

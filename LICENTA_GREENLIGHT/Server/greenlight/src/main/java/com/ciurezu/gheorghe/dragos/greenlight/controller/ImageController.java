package com.ciurezu.gheorghe.dragos.greenlight.controller;


import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ImageController {
    private final ImageService imageService;


    @PostMapping("/image/save")
    public ResponseEntity<GreenLightResponse<String>> savePhoto(@RequestBody MultipartFile photo) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/image/save").toUriString());

        String response = imageService.uploadPhoto(photo);
        GreenLightResponse<String> greenLightResponse = new GreenLightResponse<String>(response, "successfull");

        return ResponseEntity.created(uri).body(greenLightResponse);
    }

}

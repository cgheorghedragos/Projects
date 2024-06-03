package com.example.Tap.controllers;

import com.example.Tap.dto.ResponsePhotoKIt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest")
public class PhotoController {
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> handleFileUpload(@RequestParam("fileName") String fileName,
                                                @RequestPart("file") MultipartFile file) throws Exception {
        String serverUrl = "https://upload.imagekit.io/api/v1/files/upload";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String auth = "private_aEU3Ma2bzt6oSg7LkLJzub/zQiY=" + ":" +"";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        headers.set("Authorization", authHeader);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("fileName", fileName);
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();  // It's important to set the filename so that the receiving API can process it correctly.
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);


        ResponseEntity<ResponsePhotoKIt> response = restTemplate.exchange(serverUrl, HttpMethod.POST, requestEntity, ResponsePhotoKIt.class);
        return Map.of("photo_url", response.getBody().getUrl());
    }
}

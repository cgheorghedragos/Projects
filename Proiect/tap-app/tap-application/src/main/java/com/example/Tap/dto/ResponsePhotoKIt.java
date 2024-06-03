package com.example.Tap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePhotoKIt {
    private String fileId;
    private String name;
    private long size;
    private String filePath;
    private String url;
    private String fileType;
    private Integer height;
    private Integer width;
    private String thumbnailUrl;
    private List<String> AITags;  // Assuming AITags can be a list of strings, or null
}

package com.example.Tap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.Tap.entity.PhotosEntity}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhotosDTO implements Serializable {
    Long id;
    String photoUrl;
}
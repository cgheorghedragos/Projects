package com.example.Tap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.Tap.entity.ExperienceEntity}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExperienceDTO implements Serializable {
    Long id;
    Boolean isPersonalProject;
    Date startDate;
    Date endDate;
    String description;
    String location;
    List<PhotosDTO> photos;
}
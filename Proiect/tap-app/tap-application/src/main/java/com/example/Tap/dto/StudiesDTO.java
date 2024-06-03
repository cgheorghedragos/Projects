package com.example.Tap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.Tap.entity.StudiesEntity}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudiesDTO implements Serializable {
    Long id;
    Date startDate;
    Date endDate;
    String description;
    String location;
}
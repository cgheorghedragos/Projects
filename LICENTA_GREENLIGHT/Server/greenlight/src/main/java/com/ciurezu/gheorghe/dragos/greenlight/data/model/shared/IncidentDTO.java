package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @JsonIgnore
    private Long id;
    private Double latitude;
    private Double longitude;
    private String description;
    private String type;
    @JsonIgnore
    private String createdAtDate;
    @JsonIgnore
    private String createdAtTime;
}

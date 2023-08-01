package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.ActiveIncidentsPhotoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidentWithPhotoDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    private Long id;
    private Double latitude;
    private Double longitude;
    private String description;
    private String type;
    @JsonProperty(value = "created_at_date")
    private String createdAtDate;
    @JsonProperty(value = "created_at_time")
    private String createdAtTime;
    @JsonProperty(value = "photos")
    private Set<PhotoDTO> activeIncidentsPhotoEntities;
}

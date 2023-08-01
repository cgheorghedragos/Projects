package com.ciurezu.gheorghe.dragos.greenlight.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleBinDto implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    private Long id;
    private Double latitude;
    private Double longitude;
    private String type;
    @JsonProperty(value = "created_at_date")
    private String createdAtDate;
    @JsonProperty(value = "created_at_time")
    private String createdAtTime;

}

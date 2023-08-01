package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrashDTO implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    @JsonProperty("bar_code")
    private String barCode;
    @JsonProperty("category")
    private String category;
}

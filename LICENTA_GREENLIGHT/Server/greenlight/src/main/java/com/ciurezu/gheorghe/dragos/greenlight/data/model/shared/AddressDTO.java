package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    @SerializedName("id")
    private Long id;

    @JsonProperty("town")
    @Size(min = 3,message ="Enter a valid town" )
    @NotNull(message ="Enter a valid town" )
    private String town;

    @Size(min = 3,message ="Enter a valid street" )
    @NotNull(message ="Enter a valid street" )
    @JsonProperty("street")
    private String street;

    @Min(value = 0, message = "Enter a valid Street number")
    @NotNull(message = "Enter a valid Street number")
    @JsonProperty("street_nr")
    private Integer streetNr;
}

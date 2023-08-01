package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPhotoDTO {

    @Size(min = 3,message ="Enter a valid username" )
    @NotNull(message ="Enter a valid username" )
    @JsonProperty("username")
    private String username;

    @Size(min = 3,message ="Enter a valid photo_url" )
    @NotNull(message ="Enter a valid photo_url" )
    @JsonProperty("photo_url")
    private String photoUrl;
}

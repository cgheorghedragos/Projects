package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPctImg implements Serializable {

    @JsonProperty("username")
    private String username;
    @JsonProperty("score")
    private Long score;
    @JsonProperty("photo_url")
    private String photoUrl;
}

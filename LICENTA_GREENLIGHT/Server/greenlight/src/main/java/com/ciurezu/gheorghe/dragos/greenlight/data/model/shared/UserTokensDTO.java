package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokensDTO implements Serializable {

    private String username;
    private String coins;
    @JsonProperty("photo_url")
    private String photoUrl;
}

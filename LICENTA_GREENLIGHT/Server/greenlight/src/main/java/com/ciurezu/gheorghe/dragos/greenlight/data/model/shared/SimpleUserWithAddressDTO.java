package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Data
public class SimpleUserWithAddressDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;
    @JsonProperty("photo_url")
    private String photoUrl;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private Long coins = 0L;

    private Long score = 0L;

    private AddressDTO address;
}

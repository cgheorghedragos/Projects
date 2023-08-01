package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleUserDTO;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserWithAddressDTO implements Serializable {
    @SerializedName("user")
    @NotNull(message = "Enter an user")
    @Valid
    private SimpleUserDTO user;

    @SerializedName("address")
    @NotNull(message = "Add an address")
    @Valid
    private AddressDTO address;
}

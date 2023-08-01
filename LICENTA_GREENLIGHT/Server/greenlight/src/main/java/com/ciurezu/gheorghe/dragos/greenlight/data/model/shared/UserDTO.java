package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Enter a username")
    private String username;

    @NotNull(message = "Enter a password")
    @Min(value = 6, message = "password should have 6 characters")
    private String password;

    @Email(message = "Enter a e-mail", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    private String email;

    @Size(min= 10,max = 10, message = "Phone number should have 10numbers")
    @NotNull(message = "Phone number should have 10numbers")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private Long coins = 0L;
}

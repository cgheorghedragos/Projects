package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithEmailDTO {

    @NotNull(message = "Username should be not null")
    @Size(min = 3, message = "Username must have a minimum length of 3 characters")
    @Pattern(regexp = ".*", message = "Username must not be empty")
    private String username;

    @NotNull(message = "Email should be not null")
    @Email(message = "Invalid email address")
    private String email;
}

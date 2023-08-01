package com.ciurezu.gheorghe.dragos.greenlight.data.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserWithVoucher implements Serializable {

    @Min(value = 1, message = "Please enter a quantity equal with 1 or more.")
    private Integer amount;

    @NotNull(message = "Please enter the voucher id")
    private Long voucher_id;

    @NotNull(message = "Please enter the username")
    private String username;

}

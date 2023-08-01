package com.ciurezu.gheorghe.dragos.greenlight.data.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferMoneyDTO implements Serializable {
    @Min(value = 1, message = "Please enter a quantity equal with 1 or more.")
    private Integer money;

    @NotNull(message = "Please enter the voucher id")
    @JsonProperty("user_that_transfer")
    private String userThatTransfer;

    @NotNull(message = "Please enter the username")
    @JsonProperty("user_that_receive")
    private String userThatReceive;
}

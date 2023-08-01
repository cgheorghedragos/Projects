package com.ciurezu.gheorghe.dragos.greenlight.data.model.request;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.VoucherDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class VoucherWithCompanyDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("voucher")
    @Valid
    private VoucherDTO voucherDTO;


}

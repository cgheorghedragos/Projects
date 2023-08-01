package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Company;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.UserVoucher;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
public class VoucherDTO implements Serializable {
    private Long Id;

    @Min(value = 1, message = "Enter a minimum quantity equal with 1")
    private Integer quantity;

    @Min(value = 1, message = "Enter a minimum quantity equal with 1")
    private Integer price;

    @NotNull(message = "Enter a description")
    private String description;

    @Valid
    private CompanyDTO company;

}

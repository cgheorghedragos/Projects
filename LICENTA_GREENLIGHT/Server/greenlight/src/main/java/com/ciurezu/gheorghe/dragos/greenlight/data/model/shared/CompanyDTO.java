package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {
    private Long id;

    @NotNull(message = "Enter a name for the company")
    private String name;

    @NotNull(message = "Enter a username for the company")
    private String username;

    private Set<VoucherDTO> vouchers;
}

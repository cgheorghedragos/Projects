package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import lombok.Data;
import java.io.Serializable;

@Data
public class SimpleVoucherDTO implements Serializable {
    private Long id;
    private Integer quantity;

    private String description;

    private Integer price;

    private SimpleCompanyDTO company;
}

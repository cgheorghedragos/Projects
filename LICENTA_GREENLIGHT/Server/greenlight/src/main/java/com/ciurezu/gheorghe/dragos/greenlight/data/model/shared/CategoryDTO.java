package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private Long id;

    @NotNull(message = "Category should have a name")
    private String name;

}

package com.ciurezu.gheorghe.dragos.greenlight.data.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class SimpleUserCatDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Enter a username")
    private String username;

    @NotNull(message = "Enter a category")
    private String category;
}

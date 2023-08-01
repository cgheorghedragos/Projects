package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class GreenLightResponse<T> {
    private T payload;
    private String message;
}

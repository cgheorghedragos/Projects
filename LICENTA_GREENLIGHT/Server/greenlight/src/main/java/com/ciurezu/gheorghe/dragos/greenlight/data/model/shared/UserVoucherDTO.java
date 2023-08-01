package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Voucher;
import lombok.Data;



import java.io.Serializable;

@Data
public class UserVoucherDTO implements Serializable {

    private SimpleVoucherDTO voucher;

    private Long quantity;
}

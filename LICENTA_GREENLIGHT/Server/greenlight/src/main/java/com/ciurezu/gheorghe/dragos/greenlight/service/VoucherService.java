package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.UserWithVoucher;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.VoucherWithCompanyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.SimpleVoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserVoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.VoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;

import java.util.List;

public interface VoucherService {

    Boolean saveVoucher(VoucherDTO voucherDTO) throws BadRequestException;
    List<SimpleVoucherDTO> getAllVouchers();

    Boolean buyVoucher(UserWithVoucher voucher) throws BadRequestException;

    List<UserVoucherDTO> getUserVouchers(String username);
}

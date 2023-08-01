package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Company;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.UserVoucher;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Voucher;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.UserWithVoucher;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.VoucherWithCompanyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.SimpleVoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserVoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.VoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.CompanyRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.UserRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.UserVoucherRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.VoucherRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final UserVoucherRepository uvRepository;
    private final ModelMapper mapper;

    @Override
    public Boolean saveVoucher(VoucherDTO voucherDTO) throws BadRequestException {
        String username = voucherDTO.getCompany().getUsername();
        Company company = companyRepository.findByUser_Username(username);
        if (company == null) {
            throw new BadRequestException("User doesn't have a company");
        }

        Voucher voucher = mapper.map(voucherDTO, Voucher.class);
        voucher.setCompany(company);
        voucher = voucherRepository.save(voucher);

        return voucher.getId() != null;
    }

    @Override
    public List<SimpleVoucherDTO> getAllVouchers() {
        return voucherRepository.findAllActiveVouchers()
                .stream()
                .map(voucher -> mapper.map(voucher, SimpleVoucherDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean buyVoucher(UserWithVoucher voucherUser) throws BadRequestException {
        Voucher voucher = voucherRepository.findByVoucherId(voucherUser.getVoucher_id());
        User user = userRepository.findByUsername(voucherUser.getUsername());

        if (user == null || voucher == null) {
            throw new BadRequestException("Data not found");
        }

        Integer quantityToBuy = voucherUser.getAmount();
        if (voucher.getQuantity() < quantityToBuy) {
            throw new BadRequestException("You are trying to buy more vouchers than we can afford.");
        }

        Long priceToBuy = ((long) quantityToBuy * voucher.getPrice());
        if (user.getCoins() < priceToBuy) {
            throw new BadRequestException("Don't enough money");
        }

        //update quantity of vouchers
        Integer newQuantity = voucher.getQuantity() - quantityToBuy;
        voucher.setQuantity(newQuantity);
        voucherRepository.save(voucher);

        //update user money
        Long newCoins = (user.getCoins() - priceToBuy);
        user.setCoins(newCoins);
        userRepository.save(user);

        //add voucher to user
        UserVoucher userVoucher = uvRepository.findUserVoucherByUser_UsernameAndVoucher_Id(user.getUsername(), voucher.getId());
        if (userVoucher == null) {
            userVoucher = new UserVoucher();
            userVoucher.setUser(user);
            userVoucher.setVoucher(voucher);
            userVoucher.setQuantity(0L);
        }

        Long currentQty = userVoucher.getQuantity();
        userVoucher.setQuantity(currentQty + quantityToBuy);

        userVoucher = uvRepository.save(userVoucher);
        return userVoucher.getId() != null;
    }

    @Override
    public List<UserVoucherDTO> getUserVouchers(String username) {
        List<UserVoucher> uv = uvRepository.findUserVoucherByUser_UsernameAndQuantityGreaterThan0(username);

        return uv.stream()
                .map(userVoucher -> mapper.map(userVoucher, UserVoucherDTO.class))
                .collect(Collectors.toList());
    }
}

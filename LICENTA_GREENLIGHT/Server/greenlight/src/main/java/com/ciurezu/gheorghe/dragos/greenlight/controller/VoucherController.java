package com.ciurezu.gheorghe.dragos.greenlight.controller;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Company;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.StandardRoles;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.UserWithVoucher;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.VoucherWithCompanyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.VoucherDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CompanyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.GreenLightApiErrorResponse;
import com.ciurezu.gheorghe.dragos.greenlight.service.CompanyService;
import com.ciurezu.gheorghe.dragos.greenlight.service.UserService;
import com.ciurezu.gheorghe.dragos.greenlight.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/voucher")
public class VoucherController {
    private final CompanyService companyService;
    private final VoucherService voucherService;
    private final UserService userService;

    @PostMapping("/save-company")
    @Transactional(rollbackFor = {BadRequestException.class, ConflictException.class})
    public ResponseEntity<?> saveCompany(@Valid @RequestBody CompanyDTO companyDTO, BindingResult bindingResult) throws ConflictException, BadRequestException {
        ResponseEntity<?> greenLightResponse = getResponseEntity(bindingResult);
        if (greenLightResponse != null) return greenLightResponse;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/voucher/save-company").toUriString());

        Company company = companyService.saveCompany(companyDTO);
        userService.addRoleToUser(company.getUser().getUsername(), StandardRoles.SHOPPER);

        return ResponseEntity.created(uri).body(new GreenLightResponse<Boolean>(true, "success"));
    }

    @PutMapping("/update-company")
    @Transactional(rollbackFor = {BadRequestException.class, ConflictException.class})
    public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyDTO companyDTO, BindingResult bindingResult) throws ConflictException, BadRequestException {
        ResponseEntity<?> greenLightResponse = getResponseEntity(bindingResult);
        if (greenLightResponse != null) return greenLightResponse;

        Company company = companyService.updateCompany(companyDTO);
        userService.addRoleToUser(company.getUser().getUsername(),StandardRoles.SHOPPER);
        return ResponseEntity.ok().body(new GreenLightResponse<Boolean>(true, "success"));
    }

    @GetMapping("/get-companies")
    public ResponseEntity<GreenLightResponse<?>> getAllCompanies() {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(companyService.getAllCompanies(), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    static ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("\n"));

            GreenLightApiErrorResponse greenLightResponse = new GreenLightApiErrorResponse(errors, HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));

            return ResponseEntity.badRequest().body(greenLightResponse);
        }
        return null;
    }

    @PostMapping("/add-voucher")
    public ResponseEntity<?> saveVoucher(@Valid @RequestBody VoucherDTO voucher, BindingResult bindingResult) throws ConflictException, BadRequestException {
        ResponseEntity<?> response = getResponseEntity(bindingResult);
        if (response != null) return response;
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(voucherService.saveVoucher(voucher), null);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<GreenLightResponse<?>> getAllVouchers() {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(voucherService.getAllVouchers(), "success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PostMapping("/buy-voucher")
    public ResponseEntity<?> buyVoucher(@Valid @RequestBody UserWithVoucher voucher, BindingResult bindingResult) throws ConflictException, BadRequestException {
        ResponseEntity<?> response = getResponseEntity(bindingResult);
        if (response != null) return response;
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(voucherService.buyVoucher(voucher), null);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(greenLightResponse);
    }

    @GetMapping("/get-user-voucher")
    public ResponseEntity<?> getAllVoucherForUser(@RequestParam("user") String username) {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(voucherService.getUserVouchers(username), null);
        return ResponseEntity.ok().body(greenLightResponse);
    }

}

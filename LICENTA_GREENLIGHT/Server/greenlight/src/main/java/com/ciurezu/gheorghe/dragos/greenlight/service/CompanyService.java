package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Company;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CompanyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;

import java.util.List;

public interface CompanyService {
    Company saveCompany(CompanyDTO company) throws ConflictException, BadRequestException;
    List<CompanyDTO> getAllCompanies();

    Company updateCompany(CompanyDTO companyDTO) throws BadRequestException;
}

package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Company;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
    Company findByUser_Username(String username);

}
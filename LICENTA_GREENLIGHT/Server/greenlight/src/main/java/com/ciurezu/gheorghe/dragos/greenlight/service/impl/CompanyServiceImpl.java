package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Company;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Role;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.StandardRoles;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CompanyDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.CompanyRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.RoleRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.UserRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Company saveCompany(CompanyDTO companyDTO) throws ConflictException, BadRequestException {

        Company company = companyRepository.findByName(companyDTO.getName());

        if (company != null) {
            throw new ConflictException("Company already exists");
        }
        User user = userRepository.findByUsername(companyDTO.getUsername());

        if (user == null) {
            throw new BadRequestException("User doesn't exists!");
        }

        if (user.getCompany() != null) {
            throw new ConflictException("User already is assigned to a company");
        }


        company = mapper.map(companyDTO, Company.class);
        user.setCompany(company);
        company.setUser(user);

        company = companyRepository.save(company);

        return company;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companyList = companyRepository.findAll();

        return companyList.stream()
                .map(company ->
                        new CompanyDTO(company.getId(),
                                company.getName(),
                                company.getUser().getUsername(),
                                new HashSet<>()))
                .collect(Collectors.toList());
    }

    @Override
    public Company updateCompany(CompanyDTO companyDTO) throws BadRequestException {
        Optional<Company> company = companyRepository.findById(companyDTO.getId());
        if (company.isPresent()) {
            Company comp = company.get();

            if (!companyDTO.getUsername().equals(comp.getUser().getUsername())) {
                if (companyRepository.findByUser_Username(companyDTO.getUsername()) != null) {
                    Company ss= companyRepository.findByUser_Username(companyDTO.getUsername());
                    throw new BadRequestException(ss.getName()+ss.getId()+ss.getUser().getUsername());
//                    throw new BadRequestException("User is already asigned to a company");
                }
            }

            if (!companyDTO.getName().equals(comp.getName())) {
                if (companyRepository.findByName(companyDTO.getName()) != null) {
                    throw new BadRequestException("Same company already Exists!");
                }
            }

            User user = userRepository.findByUsername(companyDTO.getUsername());
            if (user == null) {
                throw new BadRequestException("User not found!");
            }

            User oldUser = comp.getUser();
            oldUser.setCompany(null);
            Role role = roleRepository.findByName(StandardRoles.SHOPPER);
            oldUser.getRoles().remove(role);
            userRepository.save(oldUser);


            comp.setName(companyDTO.getName());
            user.setCompany(comp);
            comp.setUser(user);

            comp = companyRepository.save(comp);

            return comp;
        }
        throw  new BadRequestException("Something went wrong");
    }
}

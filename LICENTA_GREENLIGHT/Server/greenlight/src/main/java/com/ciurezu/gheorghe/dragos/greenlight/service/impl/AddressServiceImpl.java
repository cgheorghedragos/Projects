package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Address;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AddressDTO;
import com.ciurezu.gheorghe.dragos.greenlight.repository.AddressRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AddressServiceImpl implements AddressService {
    private final ModelMapper mapper;
    private final AddressRepository addressRepository;

    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address addressEntity = mapper.map(addressDTO, Address.class);
        addressDTO =  mapper.map(addressRepository.save(addressEntity), AddressDTO.class);
        return addressDTO;
    }
}

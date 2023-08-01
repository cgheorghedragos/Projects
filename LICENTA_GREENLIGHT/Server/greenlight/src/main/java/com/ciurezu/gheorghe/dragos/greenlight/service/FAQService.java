package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.FAQDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;

import java.util.List;

public interface FAQService {
    Boolean updateFAQ(List<FAQDTO> faqdtos) throws BadRequestException;
    Boolean deleteFAQ(String title) throws BadRequestException;
    Boolean save(FAQDTO faqdto) throws BadRequestException, ConflictException;
    List<FAQDTO> getAllFAQ();
}

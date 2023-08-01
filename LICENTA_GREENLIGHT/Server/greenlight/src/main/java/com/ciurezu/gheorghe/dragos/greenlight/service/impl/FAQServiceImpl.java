package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Faq;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.FAQDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.FAQRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {
    private final ModelMapper modelMapper;

    private final FAQRepository faqRepository;

    @Override
    public Boolean updateFAQ(List<FAQDTO> faqdtos) throws BadRequestException {
        if (faqdtos == null) {
            throw new BadRequestException("No data found");
        }

        if (faqdtos.get(0) == null || faqdtos.get(1) == null) {
            throw new BadRequestException("No data found");
        }

        if ((faqdtos.get(0).getTitle() == null) || (faqdtos.get(1).getTitle() == null) || (faqdtos.get(1).getDescription() == null)) {
            throw new BadRequestException("Invalid data");
        }

        Faq faqEntity = faqRepository.findByTitle(faqdtos.get(0).getTitle());

        if (faqEntity == null) {
            throw new BadRequestException("Faq not found");
        }
        faqEntity.setDescription(faqdtos.get(1).getDescription());
        faqEntity.setTitle(faqdtos.get(1).getTitle());

        faqEntity = faqRepository.save(faqEntity);
        return faqEntity.getId() != null;
    }

    @Override
    public Boolean deleteFAQ(String title) throws BadRequestException {
        if (title == null) {
            throw new BadRequestException("No data found");
        }
        Integer numberDeleted = faqRepository.deleteByTitle(title);
        if (numberDeleted == 0) {
            throw new BadRequestException("Data not found");
        }
        return numberDeleted > 0;
    }

    @Override
    public Boolean save(FAQDTO faqdto) throws BadRequestException, ConflictException {
        if (faqdto == null) {
            throw new BadRequestException("No data found");
        }

        if (faqdto.getTitle() == null || faqdto.getDescription() == null) {
            throw new BadRequestException("Invalid data");
        }

        Faq faqEntity = faqRepository.findByTitle(faqdto.getTitle());

        if (faqEntity != null) {
            throw new ConflictException("Item already Exists");
        }

        faqEntity = modelMapper.map(faqdto, Faq.class);
        faqEntity = faqRepository.save(faqEntity);

        return faqEntity.getId() != null;
    }

    @Override
    public List<FAQDTO> getAllFAQ() {

        List<Faq> faqEntities = faqRepository.findAll();
        List<FAQDTO> faqdtos = faqEntities.stream().map(entity -> modelMapper.map(entity, FAQDTO.class)).collect(Collectors.toList());

        return faqdtos;
    }
}

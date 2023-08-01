package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Category;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.TrashClassifierEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.TrashDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.CategoryRepository;
import com.ciurezu.gheorghe.dragos.greenlight.repository.TrashClassifierRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.TrashClassifierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

;import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashClassifierServiceImpl implements TrashClassifierService {
    private final TrashClassifierRepository trashClassifierRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public TrashDTO getTrashByBarcode(TrashDTO trashDTO) throws BadRequestException {
        if (trashDTO == null) {
            throw new BadRequestException("Data not found");
        }

        if (trashDTO.getBarCode() == null) {
            throw new BadRequestException("Invalid BarCode!");
        }

        System.out.println(trashDTO.getBarCode());
        TrashClassifierEntity entity = trashClassifierRepository.findByBarCode(trashDTO.getBarCode());

        if (entity == null) {
            throw new BadRequestException("Barcode not found");
        }

        return mapper.map(entity, TrashDTO.class);
    }

    @Override
    public Boolean updateTrashByBarCode(TrashDTO trashDTO) throws BadRequestException {
        if (trashDTO == null) {
            throw new BadRequestException("Data not found");
        }

        if (trashDTO.getBarCode() == null || trashDTO.getCategory() == null) {
            throw new BadRequestException("Invalid data!");
        }

        Integer updatedValues = trashClassifierRepository.updateCategoryByBarcode(trashDTO.getBarCode(), trashDTO.getCategory());

        if (updatedValues == 0) {
            throw new BadRequestException("Barcode not found");
        }
        return updatedValues > 0;
    }

    @Override
    public Boolean deleteTrashByBarCode(TrashDTO trashDTO) throws BadRequestException {
        if (trashDTO == null) {
            throw new BadRequestException("Data not found");
        }

        if (trashDTO.getBarCode() == null) {
            throw new BadRequestException("Invalid data!");
        }

        Integer isDeleted = trashClassifierRepository.deleteAllByBarCode(trashDTO.getBarCode());
        if (isDeleted == 0) {
            throw new BadRequestException("Barcode not found");
        }

        return isDeleted > 0;
    }

    @Override
    public Boolean addTrash(TrashDTO trashDTO) throws BadRequestException {
        if (trashDTO == null) {
            throw new BadRequestException("Data not found");
        }

        if (trashDTO.getBarCode() == null || trashDTO.getCategory() == null) {
            System.out.println(trashDTO.getBarCode());
            System.out.println(trashDTO.getCategory());
            throw new BadRequestException("Invalid data!");
        }

        Category categoryEntity = categoryRepository.findByCategoryName(trashDTO.getCategory());

        if (categoryEntity == null) {
            throw new BadRequestException("Category not found");
        }

        TrashClassifierEntity entity = trashClassifierRepository.findByBarCode(trashDTO.getBarCode());
        if (entity != null) {
            throw new BadRequestException("Item Already exists");
        }

        entity = mapper.map(trashDTO, TrashClassifierEntity.class);
        entity.setCategory(categoryEntity);
        entity = trashClassifierRepository.save(entity);

        return entity.getId() != null;
    }

    @Override
    public List<TrashDTO> getAllTrashes() {
        return trashClassifierRepository.findAll().stream().map(trash -> {
            String barcode = trash.getBarCode();
            String category = trash.getCategory().getCategoryName();
            return new TrashDTO(barcode, category);
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }
}

package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Category;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.repository.CategoryRepository;
import com.ciurezu.gheorghe.dragos.greenlight.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Boolean saveCategory(CategoryDTO categoryDTO) throws BadRequestException {
        if (categoryDTO == null) {
            throw new BadRequestException("Data not found");
        }
        if (categoryDTO.getName() == null) {
            throw new BadRequestException("Invalid data");
        }

        Category entity = categoryRepository.findByCategoryName(categoryDTO.getName());

        if (entity != null) {
            throw new BadRequestException("Item already Exists!");
        }
        entity = mapper.map(categoryDTO, Category.class);

        entity = categoryRepository.save(entity);
        return entity.getId() != null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> mapper.map(categoryEntity, CategoryDTO.class))
                .collect(Collectors.toList());
    }
}

package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;

import java.util.List;

public interface CategoryService {
    Boolean saveCategory(CategoryDTO categoryDTO) throws BadRequestException;

    List<CategoryDTO> getAllCategories();
}

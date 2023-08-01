package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.TrashDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;

import java.util.List;

public interface TrashClassifierService {
    TrashDTO getTrashByBarcode(TrashDTO trashDTO) throws BadRequestException;
    Boolean updateTrashByBarCode(TrashDTO trashDTO) throws BadRequestException;
    Boolean deleteTrashByBarCode(TrashDTO trashDTO) throws BadRequestException;
    Boolean addTrash(TrashDTO trashDTO) throws BadRequestException;
    List<TrashDTO> getAllTrashes();

    List<CategoryDTO> getAllCategories();
}

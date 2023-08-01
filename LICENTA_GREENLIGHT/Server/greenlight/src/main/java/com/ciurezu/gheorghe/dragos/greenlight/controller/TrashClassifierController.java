package com.ciurezu.gheorghe.dragos.greenlight.controller;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.TrashDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.service.TrashClassifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trash_classifier")
public class TrashClassifierController {
    private final TrashClassifierService trashClassifierService;

    @GetMapping("/get-categories")
    public ResponseEntity<GreenLightResponse<?>> getAllCategories() throws BadRequestException {

        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(trashClassifierService.getAllCategories(),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @GetMapping("/get-trash-categories")
    public ResponseEntity<GreenLightResponse<?>> getAllTrashes() throws BadRequestException {

        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(trashClassifierService.getAllTrashes(),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PostMapping("/get-trash-category")
    public ResponseEntity<GreenLightResponse<?>> getCategoryByBarCode(@RequestBody TrashDTO trash) throws BadRequestException {

        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(trashClassifierService.getTrashByBarcode(trash),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PostMapping("/add-trash-category")
    public ResponseEntity<GreenLightResponse<?>> addCategoryByBarCode(@RequestBody TrashDTO trashDTO) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(trashClassifierService.addTrash(trashDTO),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @Transactional
    @PutMapping("/edit-trash-category")
    public ResponseEntity<GreenLightResponse<?>> editCategoryByBarCode(@RequestBody TrashDTO trashDTO) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(trashClassifierService.updateTrashByBarCode(trashDTO),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @Transactional
    @DeleteMapping("/delete-trash-category")
    public ResponseEntity<GreenLightResponse<?>> deleteCategoryByBarCode(@RequestParam("bar_code") String barcode) throws BadRequestException {
        TrashDTO trashDTO = new TrashDTO(barcode,"");
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(trashClassifierService.deleteTrashByBarCode(trashDTO),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }
}

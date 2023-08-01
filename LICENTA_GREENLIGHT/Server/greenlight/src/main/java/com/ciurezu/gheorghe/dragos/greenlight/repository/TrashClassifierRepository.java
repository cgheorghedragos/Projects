package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.TrashClassifierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TrashClassifierRepository extends JpaRepository<TrashClassifierEntity,Long> {
    TrashClassifierEntity findByBarCode(String barCode);
    Integer deleteAllByBarCode(String barCode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE trash_classifier " +
            "SET category_trash_classifier_entities = ( " +
            "SELECT cat.id FROM category cat where cat.category_name = ?2 " +
            ") " +
            "WHERE barcode = ?1 ", nativeQuery = true)
    Integer updateCategoryByBarcode(String barcode, String category);
}

package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String name);
}

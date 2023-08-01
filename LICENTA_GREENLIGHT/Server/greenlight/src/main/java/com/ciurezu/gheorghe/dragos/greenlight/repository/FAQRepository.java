package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FAQRepository extends JpaRepository<Faq,Long> {
    @Transactional
    Integer deleteByTitle(String title);
    Faq findByTitle(String title);
}

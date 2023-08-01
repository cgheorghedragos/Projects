package com.ciurezu.gheorghe.dragos.greenlight.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "trash_classifier")
public class TrashClassifierEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode")
    private String barCode;
    @ManyToOne
    @JoinColumn(name = "category_trash_classifier_entities", nullable = false)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryEntity) {
        this.category = categoryEntity;
    }
}

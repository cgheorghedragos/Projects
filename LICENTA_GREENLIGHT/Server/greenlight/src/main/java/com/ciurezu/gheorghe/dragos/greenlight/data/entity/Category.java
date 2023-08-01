package com.ciurezu.gheorghe.dragos.greenlight.data.entity;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.AchievementLevelEntity;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "trash_classifier_entities")
    @OneToMany(mappedBy = "category")
    private Set<TrashClassifierEntity> trashClassifierEntities;

    @Column(name = "achievement_level_entities")
    @OneToMany(mappedBy = "categoryEntity")
    private Set<AchievementLevelEntity> achievementLevelEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TrashClassifierEntity> getTrashClassifierEntities() {
        return trashClassifierEntities;
    }

    public void setTrashClassifierEntities(Set<TrashClassifierEntity> trashClassifierEntities) {
        this.trashClassifierEntities = trashClassifierEntities;
    }

    public Set<AchievementLevelEntity> getAchievementLevelEntities() {
        return achievementLevelEntities;
    }

    public void setAchievementLevelEntities(Set<AchievementLevelEntity> achievementLevelEntities) {
        this.achievementLevelEntities = achievementLevelEntities;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

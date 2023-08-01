package com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Category;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "achievement_level")
public class AchievementLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "achievement_name")
    private String achievementName;

    @Column(name = "level")
    private Integer level;

    @Column(name = "max_points")
    private Integer maxPoints;

    @Column(name = "image_link")
    private String imgLink;

    @OneToMany(mappedBy = "achievementLvl")
    private Set<InProgressAchievementEntity> inProgressAchievements;

    @OneToMany(mappedBy = "achievementLvl")
    private Set<CompletedAchievementEntity> completedAchievements;

    @ManyToOne
    @JoinColumn(name = "category_achievement_level_entities")
    private Category categoryEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(Category categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Set<InProgressAchievementEntity> getInProgressAchievements() {
        return inProgressAchievements;
    }

    public void setInProgressAchievements(Set<InProgressAchievementEntity> inProgressAchievements) {
        this.inProgressAchievements = inProgressAchievements;
    }

    public Set<CompletedAchievementEntity> getCompletedAchievements() {
        return completedAchievements;
    }

    public void setCompletedAchievements(Set<CompletedAchievementEntity> completedAchievements) {
        this.completedAchievements = completedAchievements;
    }
}

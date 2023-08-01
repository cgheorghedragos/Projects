package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.CompletedAchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletedAchievementRepository extends JpaRepository<CompletedAchievementEntity,Long> {
    List<CompletedAchievementEntity> findAllByUser_Username(String username);

    List<CompletedAchievementEntity> findByUser_UsernameAndAchievementLvl_CategoryEntityCategoryName(String username, String category);
}

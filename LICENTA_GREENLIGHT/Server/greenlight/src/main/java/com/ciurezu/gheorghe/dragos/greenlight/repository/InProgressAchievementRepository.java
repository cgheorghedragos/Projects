package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.InProgressAchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InProgressAchievementRepository extends JpaRepository<InProgressAchievementEntity, Long> {
    InProgressAchievementEntity findByUser_UsernameAndAchievementLvl_CategoryEntity_CategoryName(String username, String categoryName);
    List<InProgressAchievementEntity> findAllByUser_Username(String username);
}

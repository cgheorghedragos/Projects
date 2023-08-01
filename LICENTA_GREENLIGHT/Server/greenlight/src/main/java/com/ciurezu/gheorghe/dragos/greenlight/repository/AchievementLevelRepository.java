package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.AchievementLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AchievementLevelRepository extends JpaRepository<AchievementLevelEntity, Long> {
    AchievementLevelEntity findByAchievementName(String name);

    @Query(value = "SELECT al.* " +
            "FROM achievement_level al " +
            "WHERE NOT EXISTS ( " +
            "SELECT * FROM completed_achievement ca " +
            "INNER JOIN users u ON ca.users_id = u.id " +
            "WHERE ca.achievement_level_id = al.id " +
            "AND u.username = ?1) " +
            "AND NOT EXISTS (SELECT * FROM in_progress_achievement ia " +
            "INNER JOIN users u ON ia.users_id = u.id " +
            "WHERE ia.achievement_level_id = al.id " +
            "AND u.username = ?1) AND al.level = 0 "
            , nativeQuery = true)
    List<AchievementLevelEntity> findAllByLevel0AndName(String name);



    @Query(value = "SELECT al.* " +
            "FROM achievement_level al  LEFT JOIN  category cat ON " +
            "al.category_achievement_level_entities = cat.id " +
            "WHERE cat.category_name =?1 " +
            "AND al.level =?2 ", nativeQuery = true)
    AchievementLevelEntity findByCategoryNameAndLevel(String categoryName, Integer Level);


}

package com.ciurezu.gheorghe.dragos.greenlight.service;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleAchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleInactiveAchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementLevelDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserDTO;

import java.util.List;

public interface AchievementService {
    Boolean saveAchievementLevel(AchievementLevelDTO achievementLevelDTO);

    Boolean addLevel0Achievements(UserDTO user);

    List<SimpleAchievementDTO> getAllAchievementForUser(String username);

    List<SimpleInactiveAchievementDTO> getAllInactiveAchievementsForUser(String username);

    Boolean updateAchievementProgress(UserDTO userDTO, CategoryDTO categoryDTO);


    Boolean saveAchievement(AchievementDTO achievementDTO);
}

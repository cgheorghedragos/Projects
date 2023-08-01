package com.ciurezu.gheorghe.dragos.greenlight.service.impl;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.Category;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.AchievementLevelEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.InProgressAchievementEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleAchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.request.SimpleInactiveAchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementLevelDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.CategoryDTO;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.UserDTO;
import com.ciurezu.gheorghe.dragos.greenlight.repository.*;
import com.ciurezu.gheorghe.dragos.greenlight.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementLevelRepository achievementLevelRepository;
    private final InProgressAchievementRepository inProgressAchievementRepository;
    private final CompletedAchievementRepository completedAchievementRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public Boolean saveAchievementLevel(AchievementLevelDTO achievementLevelDTO) {
        AchievementLevelEntity entity = mapper.map(achievementLevelDTO, AchievementLevelEntity.class);

        Category category = categoryRepository.findByCategoryName(achievementLevelDTO.getCategory().getName());

        entity.setCategoryEntity(category);

        entity = achievementLevelRepository.save(entity);
        return entity.getId() != null;
    }

    @Override
    public Boolean addLevel0Achievements(UserDTO user) {
        User userEnt = userRepository.findByUsername(user.getUsername());

        List<AchievementLevelEntity> achievements = achievementLevelRepository.findAllByLevel0AndName(user.getUsername());

        achievements.forEach(achievement -> {

            InProgressAchievementEntity inProgAchievement = new InProgressAchievementEntity();
            inProgAchievement.setAchievementLvl(achievement);
            inProgAchievement.setCurrentProgress(0);
            inProgAchievement.setUser(userEnt);
            inProgressAchievementRepository.save(inProgAchievement);
        });

        return true;
    }

    @Override
    public List<SimpleAchievementDTO> getAllAchievementForUser(String username) {
        List<SimpleAchievementDTO> achievementDTO = inProgressAchievementRepository.findAllByUser_Username(username).stream().map(inProgress -> mapper.map(inProgress, SimpleAchievementDTO.class)).collect(Collectors.toList());

        return achievementDTO;
    }

    @Override
    public List<SimpleInactiveAchievementDTO> getAllInactiveAchievementsForUser(String username) {
        List<SimpleInactiveAchievementDTO> achievementDTO = completedAchievementRepository.findAllByUser_Username(username).stream().map(completed -> mapper.map(completed, SimpleInactiveAchievementDTO.class)).collect(Collectors.toList());

        return achievementDTO;
    }

    @Override
    public Boolean updateAchievementProgress(UserDTO userDTO, CategoryDTO categoryDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        return null;
    }

    @Override
    public Boolean saveAchievement(AchievementDTO achievementDTO) {
        return null;
    }
}

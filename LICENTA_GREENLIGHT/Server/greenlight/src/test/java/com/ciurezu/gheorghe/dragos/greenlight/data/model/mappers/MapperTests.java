package com.ciurezu.gheorghe.dragos.greenlight.data.model.mappers;


import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.AchievementLevelEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementLevelDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTests {
    private final Mapper mapper = new Mapper(new PhotoMapper(), new IncidentMapper(), new AddressMapper(), new UserMapper(new AddressMapper()), new AchievementMapper(), new CategoryMapper());

    @Test
    public void TestentityToDto() {
        AchievementLevelEntity achievementLevel = new AchievementLevelEntity();
//        achievementLevel.setAchievementName("test");
//        achievementLevel.setCategoryEntity(new CategoryEntity());
//        achievementLevel.setId(1L);
//        achievementLevel.setLevel(1);
//        achievementLevel.setCompletedAchievements(new HashSet<>());
//        achievementLevel.setImgLink("link");
//        achievementLevel.setInProgressAchievements(new HashSet<>());
//        achievementLevel.setMaxPoints(100);
AchievementLevelDTO dto = new AchievementLevelDTO();
//        AchievementLevelDTO dto = (AchievementLevelDTO) mapper.entityToDto(achievementLevel);
//        assertNull(dto.getCategory());
//        assertNull(dto.getId());
//        assertEquals(dto.getLevel(), 1);
//        assertEquals(dto.getMaxPoints(), 100);
//        assertEquals(dto.getImgLink(), "link");
        assertEquals(dto.getAchievementName(), "test");
    }
}

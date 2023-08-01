package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class AchievementDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer currentProgress;
    private UserDTO user;
    private AchievementLevelDTO achievementLvl;
}

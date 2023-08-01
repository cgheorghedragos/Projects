package com.ciurezu.gheorghe.dragos.greenlight.data.model.request;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.AchievementLevelDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SimpleInactiveAchievementDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("created_on")
    private LocalDateTime localDateTime;
    @JsonProperty("achievement_lvl")
    private AchievementLevelDTO achievementLvl;


}

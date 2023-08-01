package com.ciurezu.gheorghe.dragos.greenlight.data.model.shared;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.CompletedAchievementEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.InProgressAchievementEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementLevelDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @Valid
    @NotNull(message = "Enter the category")
    private CategoryDTO category;

    @Size(min = 4,message = "Enter an achievement name")
    @NotNull
    @JsonProperty
    private String achievementName;

    @Min(value = 0, message = "Enter a level.")
    @NotNull(message = "Enter a level.")
    private Integer level;

    @Min(value = 0, message = "Enter a level.")
    @NotNull(message = "Enter a level.")
    private Integer maxPoints;

    @Size(min = 4,message = "Enter an achievement name")
    @NotNull(message = "Enter an achievement name.")
    private String imgLink;
}

package com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "completed_achievement")
public class CompletedAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_on")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "achievement_level_id", nullable = false)
    private AchievementLevelEntity achievementLvl;

    @PrePersist
    public void prePersist() {
        localDateTime = LocalDateTime.now();
    }
}

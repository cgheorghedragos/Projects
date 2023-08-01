package com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "in_progress_achievement")
public class InProgressAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "current_progress")
    private Integer currentProgress;

    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "achievement_level_id",nullable = false)
    private AchievementLevelEntity achievementLvl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(Integer currentProgress) {
        this.currentProgress = currentProgress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AchievementLevelEntity getAchievementLvl() {
        return achievementLvl;
    }

    public void setAchievementLvl(AchievementLevelEntity achievementLvl) {
        this.achievementLvl = achievementLvl;
    }
}


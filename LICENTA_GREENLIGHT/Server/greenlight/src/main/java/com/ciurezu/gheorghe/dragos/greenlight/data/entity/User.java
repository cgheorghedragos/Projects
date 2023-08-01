package com.ciurezu.gheorghe.dragos.greenlight.data.entity;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.CompletedAchievementEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.InProgressAchievementEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -5460812968266262395L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "coins", nullable = false)
    private Long coins = 0L;

    @Column(name = "score", nullable = false)
    private Long score = 0L;

    @Setter
    @Getter
    @Column(name = "photo_url")
    private String photoUrl = "https://storage.googleapis.com/download/storage/v1/b/greenlight_photos2/o/default_user_photo.jpg?generation=1687290832064032&alt=media";


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address = new Address();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<InProgressAchievementEntity> inProgressAchievement;

    @OneToMany(mappedBy = "user")
    private Set<CompletedAchievementEntity> completedAchievement;

    @OneToOne
    @Setter
    @Getter
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserVoucher> userVouchers;

    public Set<UserVoucher> getUserVouchers() {
        return userVouchers;
    }

    public void setUserVouchers(Set<UserVoucher> userVouchers) {
        this.userVouchers = userVouchers;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Set<InProgressAchievementEntity> getInProgressAchievement() {
        return inProgressAchievement;
    }

    public void setInProgressAchievement(Set<InProgressAchievementEntity> inProgressAchievement) {
        this.inProgressAchievement = inProgressAchievement;
    }

    public Set<CompletedAchievementEntity> getCompletedAchievement() {
        return completedAchievement;
    }

    public void setCompletedAchievement(Set<CompletedAchievementEntity> completedAchievement) {
        this.completedAchievement = completedAchievement;
    }
}

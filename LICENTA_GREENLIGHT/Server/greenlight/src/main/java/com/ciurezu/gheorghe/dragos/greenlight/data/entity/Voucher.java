package com.ciurezu.gheorghe.dragos.greenlight.data.entity;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.AchievementLevelEntity;
import com.ciurezu.gheorghe.dragos.greenlight.data.entity.achievement.InProgressAchievementEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Voucher")
@Setter
@Getter
public class Voucher implements Serializable {
    @Serial
    private static final long serialVersionUID = -5460812968266262395L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @OneToMany(mappedBy = "voucher", fetch = FetchType.EAGER)
    private Set<UserVoucher> userVouchers;
}

package com.ciurezu.gheorghe.dragos.greenlight.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_voucher")
@Setter
@Getter
public class UserVoucher implements Serializable {
    @Serial
    private static final long serialVersionUID = -5460812968266262395L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Column(name = "quantity")
    private Long quantity;
}

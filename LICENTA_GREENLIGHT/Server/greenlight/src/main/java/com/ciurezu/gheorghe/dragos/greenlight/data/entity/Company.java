package com.ciurezu.gheorghe.dragos.greenlight.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "company")
@Setter
@Getter
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "company")
    private User user;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Voucher> vouchers;

}

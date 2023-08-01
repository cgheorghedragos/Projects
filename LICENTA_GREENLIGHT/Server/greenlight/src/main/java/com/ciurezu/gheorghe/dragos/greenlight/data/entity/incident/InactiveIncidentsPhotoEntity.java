package com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.InactiveIncidentsEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "inactive_incidents_photo")
public class InactiveIncidentsPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "solved_on")
    private String solvedOn;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "inactive_incidents_id", nullable = false)
    private InactiveIncidentsEntity inactiveIncidents;

    @PrePersist
    public void prePersist(){
        solvedOn = LocalDateTime.now().toString();
    }
}

package com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.ActiveIncidentsEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_incidents_photo")
public class ActiveIncidentsPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "created_on")
    private String localDateTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "active_incidents_id",nullable = false)
    private ActiveIncidentsEntity activeIncidents;

    @PrePersist
    public void prePersist(){
        localDateTime = LocalDateTime.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ActiveIncidentsEntity getActiveIncidents() {
        return activeIncidents;
    }

    public void setActiveIncidents(ActiveIncidentsEntity activeIncidents) {
        this.activeIncidents = activeIncidents;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }
}

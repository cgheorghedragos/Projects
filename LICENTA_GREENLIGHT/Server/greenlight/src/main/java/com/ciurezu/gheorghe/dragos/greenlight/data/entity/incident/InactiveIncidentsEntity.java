package com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "inactive_incidents")
public class InactiveIncidentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at_date")
    private String createdAtDate;

    @Column(name = "created_at_time")
    private String createdAtTime;

    @OneToMany(mappedBy = "inactiveIncidents",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<InactiveIncidentsPhotoEntity> inactiveIncidentsPhotoEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAtDate() {
        return createdAtDate;
    }

    public void setCreatedAtDate(String createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    public String getCreatedAtTime() {
        return createdAtTime;
    }

    public void setCreatedAtTime(String createdAtTime) {
        this.createdAtTime = createdAtTime;
    }

    public Set<InactiveIncidentsPhotoEntity> getInactiveIncidentsPhotoEntities() {
        return inactiveIncidentsPhotoEntities;
    }

    public void setInactiveIncidentsPhotoEntities(Set<InactiveIncidentsPhotoEntity> inactiveIncidentsPhotoEntities) {
        this.inactiveIncidentsPhotoEntities = inactiveIncidentsPhotoEntities;
    }
}

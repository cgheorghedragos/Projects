package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.ActiveIncidentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveIncidentRepository extends JpaRepository<ActiveIncidentsEntity, Long> {
    @Query(value = "SELECT * FROM active_incidents WHERE active_incidents.id = ?1", nativeQuery = true)
    ActiveIncidentsEntity findIncidentsById(Long id);

    @Query(value = "SELECT * FROM active_incidents WHERE active_incidents.type <> 'recyclebin'", nativeQuery = true)
    List<ActiveIncidentsEntity> findAllIncidents();
    @Query(value = "SELECT * FROM active_incidents WHERE active_incidents.type = 'recyclebin'", nativeQuery = true)
    List<ActiveIncidentsEntity> findAllRecycleBin();
}

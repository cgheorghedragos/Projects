package com.ciurezu.gheorghe.dragos.greenlight.repository;

import com.ciurezu.gheorghe.dragos.greenlight.data.entity.incident.InactiveIncidentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InactiveIncidentRepository extends JpaRepository<InactiveIncidentsEntity,Long> {

}

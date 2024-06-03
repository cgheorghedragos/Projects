package com.example.Tap.dao;

import com.example.Tap.entity.StudiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudiesRepository extends JpaRepository<StudiesEntity, Long> {
}

package com.danielesteban.best_travel.domain.repositories;

import com.danielesteban.best_travel.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
}

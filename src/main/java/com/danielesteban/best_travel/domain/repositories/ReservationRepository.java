package com.danielesteban.best_travel.domain.repositories;

import com.danielesteban.best_travel.domain.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}

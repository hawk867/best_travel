package com.danielesteban.best_travel.domain.repositories;

import com.danielesteban.best_travel.domain.entities.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {
}

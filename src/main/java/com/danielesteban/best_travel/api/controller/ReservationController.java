package com.danielesteban.best_travel.api.controller;

import com.danielesteban.best_travel.api.models.request.ReservationRequest;
import com.danielesteban.best_travel.api.models.responses.ReservationResponse;
import com.danielesteban.best_travel.infraestructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(reservationService.create(reservationRequest));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(reservationService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> put(@PathVariable("id") UUID id, @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(reservationService.update(reservationRequest, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> delete(@PathVariable("id") UUID id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(@RequestParam Long reservationId) {
        return ResponseEntity.ok(Collections.singletonMap("reservationPrice", reservationService.findPrice(reservationId)));
    }
}

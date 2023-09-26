package com.danielesteban.best_travel.api.controller;

import com.danielesteban.best_travel.api.models.request.TicketRequest;
import com.danielesteban.best_travel.api.models.responses.TicketResponse;
import com.danielesteban.best_travel.infraestructure.abstract_services.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {

    private final ITicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.create(ticketRequest));
    }
}

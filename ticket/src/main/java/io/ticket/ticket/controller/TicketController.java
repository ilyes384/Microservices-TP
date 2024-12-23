package io.ticket.ticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.ticket.ticket.model.Ticket;
import io.ticket.ticket.model.TypeTicket;
import io.ticket.ticket.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/internaute/{internauteId}")
    public ResponseEntity<List<Ticket>> getTicketsByInternaute(@PathVariable Long internauteId) {
        return ResponseEntity.ok(ticketService.getTicketsByInternauteId(internauteId));
    }

    @GetMapping("/evenement/{evenementId}")
    public ResponseEntity<List<Ticket>> getTicketsByEvenement(@PathVariable Long evenementId) {
        return ResponseEntity.ok(ticketService.getTicketsByEvenementId(evenementId));
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        try {
            return ResponseEntity.ok(ticketService.saveTicket(ticket));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.getTicketById(id)
                .map(existingTicket -> {
                    ticket.setIdTicket(existingTicket.getIdTicket());
                    return ResponseEntity.ok(ticketService.saveTicket(ticket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    // Get the most active internaute
    @GetMapping("/internaute/actif")
    public ResponseEntity<String> internauteLePlusActif() {
        return ResponseEntity.ok(ticketService.internauteLePlusActif());
    }

    // Add tickets and assign them to an event and an internaute
    @PostMapping("/ajouter")
    public ResponseEntity<List<Ticket>> ajouterTicketsEtAffecterAEvenementEtInternaute(
            @RequestBody List<Ticket> tickets,
            @RequestParam Long idEvenement, @RequestParam Long idInternaute) {
        return ResponseEntity
                .ok(ticketService.ajouterTicketsEtAffecterAEvenementEtInternaute(tickets, idEvenement, idInternaute));
    }

    // Get the amount recovered by event and ticket type
    @GetMapping("/montant/{nomEvt}/{typeTicket}")
    public ResponseEntity<Double> montantRecupereParEvtEtTypeTicket(@PathVariable String nomEvt,
            @PathVariable String typeTicket) {
        TypeTicket type = TypeTicket.valueOf(typeTicket);
        return ResponseEntity.ok(ticketService.montantRecupereParEvtEtTypeTicket(nomEvt, type));
    }
}
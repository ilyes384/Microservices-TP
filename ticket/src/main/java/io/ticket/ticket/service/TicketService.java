package io.ticket.ticket.service;

import java.util.List;
import java.util.Optional;

import io.ticket.ticket.model.Ticket;
import io.ticket.ticket.model.TypeTicket;

public interface TicketService {

    public List<Ticket> getAllTickets();

    public Optional<Ticket> getTicketById(Long id);

    public List<Ticket> getTicketsByInternauteId(Long internauteId);

    public List<Ticket> getTicketsByEvenementId(Long evenementId);

    public Ticket saveTicket(Ticket ticket);

    public void deleteTicket(Long id);

    public String internauteLePlusActif();

    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(List<Ticket> tickets, Long idEvenement,
            Long idInternaute);

    public Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket);

}
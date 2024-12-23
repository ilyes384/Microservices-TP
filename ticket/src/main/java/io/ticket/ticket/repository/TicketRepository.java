package io.ticket.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.ticket.ticket.model.Ticket;

import java.util.List;
import io.ticket.ticket.model.TypeTicket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByIdInternaute(Long idInternaute);

    List<Ticket> findByIdEvenement(Long idEvenement);

    List<Ticket> findByIdEvenementAndTypeTicket(Long idEvenement, TypeTicket typeTicket);
}
package io.ticket.ticket.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import io.ticket.ticket.client.EvenementClient;
import io.ticket.ticket.client.InternauteClient;
import io.ticket.ticket.model.Evenement;
import io.ticket.ticket.model.Internaute;
import io.ticket.ticket.model.Ticket;
import io.ticket.ticket.model.TypeTicket;
import io.ticket.ticket.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final InternauteClient internauteClient;
    private final EvenementClient evenementClient;

    public TicketServiceImpl(TicketRepository ticketRepository, InternauteClient internauteClient,
            EvenementClient evenementClient) {
        this.ticketRepository = ticketRepository;
        this.internauteClient = internauteClient;
        this.evenementClient = evenementClient;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getTicketsByInternauteId(Long internauteId) {
        return ticketRepository.findByIdInternaute(internauteId);
    }

    public List<Ticket> getTicketsByEvenementId(Long evenementId) {
        return ticketRepository.findByIdEvenement(evenementId);
    }

    public boolean verifyInternauteExists(Long internauteId) {
        try {
            ResponseEntity<Internaute> response = internauteClient.getInternauteById(internauteId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Internaute existe
                return true;
            }
            return false;
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    public Long getEvenementRemainingPlaces(Long evenementId) {
        try {
            System.out.println("evenementId: " + evenementId);
            ResponseEntity<Evenement> response = evenementClient.getEvenementById(evenementId);
            System.out.println(response.getStatusCode());
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Evenement evenement = response.getBody();
                return evenement.getNbPIacesRestants();
            }
            return -1l;
        } catch (FeignException.NotFound e) {
            return -1l;
        }
    }

    // Mettre à jour le nombre de places restantes
    public boolean updateEvenementRemainingPlaces(Long evenementId) {
        ResponseEntity<Evenement> response = evenementClient.getEvenementById(evenementId);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Evenement evenement = response.getBody();
            System.out.println("evenement.getNbPIacesRestants(): " + evenement.getNbPIacesRestants());
            evenement.setNbPIacesRestants(evenement.getNbPIacesRestants() - 1);
            System.out.println("Success updateEvenementRemainingPlaces");
            evenementClient.updateEvenement(evenementId, evenement);
            return true;
        }
        return false;
    }

    public Ticket saveTicket(Ticket ticket) {
        // Vérification de l'internaute
        if (!verifyInternauteExists(ticket.getIdInternaute())) {
            throw new IllegalArgumentException("L'internaute avec l'ID " + ticket.getIdInternaute() + " n'existe pas.");
        }

        // Verification de l'evenement et des places restantes
        Long remainingPlaces = getEvenementRemainingPlaces(ticket.getIdEvenement());
        if (remainingPlaces == -1) {
            throw new IllegalArgumentException("L'événement avec l'ID " + ticket.getIdEvenement() + " n'existe pas.");
        }
        if (remainingPlaces == 0) {
            throw new java.lang.UnsupportedOperationException("nombre de places demandées indisponible");
        }

        // Mettre à jour le nombre de places restantes
        if (!updateEvenementRemainingPlaces(ticket.getIdEvenement())) {
            throw new IllegalArgumentException(
                    "Erreur lors de la mise à jour du nombre de places restantes pour l'événement avec l'ID "
                            + ticket.getIdEvenement() + ".");
        }

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public String internauteLePlusActif() {
        List<Ticket> tickets = ticketRepository.findAll();
        Long idInternaute = -1l;
        Long maxTickets = 0l;
        for (Ticket ticket : tickets) {
            Long internauteId = ticket.getIdInternaute();
            Long count = tickets.stream().filter(t -> t.getIdInternaute().equals(internauteId)).count();
            if (count > maxTickets) {
                maxTickets = count;
                idInternaute = internauteId;
            }
        }
        if (idInternaute != -1l) {
            ResponseEntity<Internaute> response = internauteClient.getInternauteById(idInternaute);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Internaute internaute = response.getBody();

                return internaute.getIdentifiant();
            }
        }
        return "Aucun internaute n'a acheté de tickets.";
    }

    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(List<Ticket> tickets, Long idEvenement,
            Long idInternaute) {
        List<Ticket> ticketsAjoutes = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticket.setIdEvenement(idEvenement);
            ticket.setIdInternaute(idInternaute);
            saveTicket(ticket);
        }
        return ticketsAjoutes;
    }

    public Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket) {
        // Get evenements by name
        List<Evenement> evenements = evenementClient.getEvenementByName(nomEvt).getBody();
        if (evenements == null || evenements.isEmpty()) {
            throw new IllegalArgumentException("L'événement avec le nom " + nomEvt + " n'existe pas.");
        }
        Evenement evt = evenements.get(0);

        List<Ticket> tickets = ticketRepository.findByIdEvenementAndTypeTicket(evt.getIdEvenement(), typeTicket);
        Double montant = 0.0;
        for (Ticket ticket : tickets) {
            montant += ticket.getPrixTicket();
        }
        return montant;
    }
}

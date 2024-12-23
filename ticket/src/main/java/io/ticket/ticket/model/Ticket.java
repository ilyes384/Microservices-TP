package io.ticket.ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    private String codeTicket;

    private Double prixTicket;

    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;

    private Long idInternaute; // Référence à l'internaute (d'un autre microservice)

    private Long idEvenement; // Référence à l'événement (d'un autre microservice)
}

package io.ticket.ticket.model;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Evenement {
    private Long idEvenement;

    private String nomEvenement;

    private Long nbPIacesRestants;

    private LocalDate dateEvenement;

    private Set<Categorie> categories;
}
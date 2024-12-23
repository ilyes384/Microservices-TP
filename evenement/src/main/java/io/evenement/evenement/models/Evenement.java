package io.evenement.evenement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;

    private String nomEvenement;

    private Long nbPIacesRestants;

    private LocalDate dateEvenement;

    @ManyToMany
    @JoinTable(name = "evenement_categorie", joinColumns = @JoinColumn(name = "evenement_id"), inverseJoinColumns = @JoinColumn(name = "categorie_id"))
    private Set<Categorie> categories;
}
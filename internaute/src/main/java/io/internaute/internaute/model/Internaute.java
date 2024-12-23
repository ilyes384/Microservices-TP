package io.internaute.internaute.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Internaute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInternaute;

    private String identifiant;

    @Enumerated(EnumType.STRING)
    private TrancheAge trancheAge;
}
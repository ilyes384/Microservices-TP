package io.ticket.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Internaute {

    private Long idInternaute;

    private String identifiant;

    private TrancheAge trancheAge;

}

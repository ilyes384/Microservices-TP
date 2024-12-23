package io.ticket.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Categorie {
    private Long idCategorie;

    private String codeCategorie;

    private String nomCategorie;
}
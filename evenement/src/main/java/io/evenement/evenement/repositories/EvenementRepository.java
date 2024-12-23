package io.evenement.evenement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evenement.evenement.models.Evenement;
import java.util.List;
import io.evenement.evenement.models.Categorie;
import java.util.Set;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    List<Evenement> findByCategories(Set<Categorie> categories);

    Evenement findByNomEvenement(String nomEvt);

    List<Evenement> findByNomEvenementContaining(String name);
}

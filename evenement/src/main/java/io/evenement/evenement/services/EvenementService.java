package io.evenement.evenement.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import io.evenement.evenement.models.Categorie;
import io.evenement.evenement.models.Evenement;
import io.evenement.evenement.repositories.CategorieRepository;
import io.evenement.evenement.repositories.EvenementRepository;

import java.util.List;
import java.util.Set;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final CategorieRepository categorieRepository;

    private static final Logger logger = LoggerFactory.getLogger(EvenementService.class);

    public EvenementService(EvenementRepository evenementRepository, CategorieRepository categorieRepository) {
        this.evenementRepository = evenementRepository;
        this.categorieRepository = categorieRepository;
    }

    public Evenement ajouterEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Evenement updateEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public Evenement getEvenementById(Long id) {
        return evenementRepository.findById(id).orElseThrow(() -> new RuntimeException("Evenement not found"));
    }

    public void deleteEvenement(Long id) {
        Evenement evenement = evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evenement not found"));
        evenementRepository.delete(evenement);
    }

    public void afficherEvenementsParCategorie() {
        List<Categorie> categories = categorieRepository.findAll();

        for (Categorie categorie : categories) {
            logger.info("Categorie: {}", categorie.getNomCategorie());

            List<Evenement> evenements = evenementRepository.findByCategories(Set.of(categorie));

            if (evenements.isEmpty()) {
                logger.info("  Aucun événement planifié pour cette catégorie.");
            } else {
                evenements.forEach(evenement -> logger.info("  {} planifié le {}", evenement.getNomEvenement(),
                        evenement.getDateEvenement()));
                ;
            }
        }
    }

    public List<Evenement> getEvenementsByName(String name) {
        return evenementRepository.findByNomEvenementContaining(name);
    }
}
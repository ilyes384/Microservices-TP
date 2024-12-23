package io.evenement.evenement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EvenementScheduler {

    private static final Logger logger = LoggerFactory.getLogger(EvenementScheduler.class);

    private final EvenementService evenementService;

    public EvenementScheduler(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @Scheduled(fixedRate = 15000) // Exécute toutes les 15 secondes
    public void listeEvenementsParCategorie() {
        logger.info("Début de l'exécution du scheduler");
        evenementService.afficherEvenementsParCategorie();
        logger.info("Fin de l'exécution du scheduler");
    }
}
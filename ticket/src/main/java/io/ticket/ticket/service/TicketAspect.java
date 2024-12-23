package io.ticket.ticket.service;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TicketAspect {

    private static final Logger logger = LoggerFactory.getLogger(TicketAspect.class);

    /**
     * Intercepte les exceptions lancées par la méthode
     * ajouterTicketsEtAffecterAEvenementEtInternaute.
     */
    @AfterThrowing(pointcut = "execution(* io.ticket.ticket.service.TicketService.ajouterTicketsEtAffecterAEvenementEtInternaute(..))", throwing = "exception")
    public void handleException(Throwable exception) {
        // Afficher le message spécifique
        logger.info("Le nombre de places restantes dépasse le nombre de tickets demandés");
        // Log supplémentaire pour débogage si nécessaire
        logger.error("Exception interceptée : ", exception);
    }
}
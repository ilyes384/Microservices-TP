package io.ticket.ticket.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import io.ticket.ticket.model.Evenement;

@FeignClient(name = "EVENEMENT")
public interface EvenementClient {
    @GetMapping("/api/evenements/{id}")
    ResponseEntity<Evenement> getEvenementById(@PathVariable("id") Long id);

    @PutMapping("/api/evenements/{id}")
    ResponseEntity<Evenement> updateEvenement(@PathVariable("id") Long id, Evenement evenement);

    @GetMapping("/api/evenements/name/{name}")
    ResponseEntity<List<Evenement>> getEvenementByName(@PathVariable("name") String name);
}

package io.evenement.evenement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.evenement.evenement.models.Evenement;
import io.evenement.evenement.services.EvenementService;

import java.util.List;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @PostMapping
    public ResponseEntity<Evenement> ajouterEvenement(@RequestBody Evenement evenement) {
        Evenement createdEvenement = evenementService.ajouterEvenement(evenement);
        return new ResponseEntity<>(createdEvenement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evenement> modifierEvenement(@PathVariable Long id, @RequestBody Evenement evenement) {
        Evenement updatedEvenement = evenementService.updateEvenement(evenement);
        return ResponseEntity.ok(updatedEvenement);
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllEvenements() {
        List<Evenement> evenements = evenementService.getAllEvenements();
        return ResponseEntity.ok(evenements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable Long id) {
        Evenement evenement = evenementService.getEvenementById(id);
        return ResponseEntity.ok(evenement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Evenement>> getEvenementsByName(@PathVariable String name) {
        List<Evenement> evenements = evenementService.getEvenementsByName(name);
        return ResponseEntity.ok(evenements);
    }
}

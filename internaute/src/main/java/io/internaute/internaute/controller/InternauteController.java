package io.internaute.internaute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.internaute.internaute.model.Internaute;
import io.internaute.internaute.service.InternauteService;

import java.util.List;

@RestController
@RequestMapping("/api/internautes")
public class InternauteController {

    private final InternauteService internauteService;

    public InternauteController(InternauteService internauteService) {
        this.internauteService = internauteService;
    }

    @GetMapping
    public ResponseEntity<List<Internaute>> getAllInternautes() {
        return ResponseEntity.ok(internauteService.getAllInternautes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internaute> getInternauteById(@PathVariable Long id) {
        return internauteService.getInternauteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Internaute> createInternaute(@RequestBody Internaute internaute) {
        return ResponseEntity.ok(internauteService.ajouterInternaute(internaute));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internaute> updateInternaute(@PathVariable Long id, @RequestBody Internaute internaute) {
        return internauteService.getInternauteById(id)
                .map(existingInternaute -> {
                    internaute.setIdInternaute(existingInternaute.getIdInternaute());
                    return ResponseEntity.ok(internauteService.ajouterInternaute(internaute));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternaute(@PathVariable Long id) {
        internauteService.deleteInternaute(id);
        return ResponseEntity.noContent().build();
    }
}
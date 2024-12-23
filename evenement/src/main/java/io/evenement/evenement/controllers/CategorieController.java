package io.evenement.evenement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.evenement.evenement.models.Categorie;
import io.evenement.evenement.services.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}
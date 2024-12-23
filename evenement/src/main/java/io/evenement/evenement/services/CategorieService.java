package io.evenement.evenement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.evenement.evenement.models.Categorie;
import io.evenement.evenement.repositories.CategorieRepository;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElseThrow(() -> new RuntimeException("Categorie not found"));
    }

    public void deleteCategorie(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
        categorieRepository.delete(categorie);
    }
}
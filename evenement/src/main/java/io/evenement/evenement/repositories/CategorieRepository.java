package io.evenement.evenement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evenement.evenement.models.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
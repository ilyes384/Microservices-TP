package io.internaute.internaute.service;

import io.internaute.internaute.model.Internaute;

import java.util.List;
import java.util.Optional;

public interface InternauteService {

    List<Internaute> getAllInternautes();

    Optional<Internaute> getInternauteById(Long id);

    Internaute ajouterInternaute(Internaute internaute);

    void deleteInternaute(Long id);
}
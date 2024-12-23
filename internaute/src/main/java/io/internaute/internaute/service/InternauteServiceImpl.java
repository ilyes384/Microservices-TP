package io.internaute.internaute.service;

import io.internaute.internaute.repository.InternauteRepository;
import io.internaute.internaute.model.Internaute;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class InternauteServiceImpl implements InternauteService {

    private final InternauteRepository internauteRepository;

    public InternauteServiceImpl(InternauteRepository internauteRepository) {
        this.internauteRepository = internauteRepository;
    }

    @Override
    public List<Internaute> getAllInternautes() {
        return internauteRepository.findAll();
    }

    @Override
    public Optional<Internaute> getInternauteById(Long id) {
        return internauteRepository.findById(id);
    }

    @Override
    public Internaute ajouterInternaute(Internaute internaute) {
        return internauteRepository.save(internaute);
    }

    @Override
    public void deleteInternaute(Long id) {
        internauteRepository.deleteById(id);
    }
}
package io.internaute.internaute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.internaute.internaute.model.Internaute;

@Repository
public interface InternauteRepository extends JpaRepository<Internaute, Long> {
}
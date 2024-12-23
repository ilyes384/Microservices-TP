package io.ticket.ticket.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.ticket.ticket.model.Internaute;

@FeignClient(name = "INTERNAUTE")
public interface InternauteClient {
    @GetMapping("/api/internautes/{id}")
    ResponseEntity<Internaute> getInternauteById(@PathVariable("id") Long id);
}
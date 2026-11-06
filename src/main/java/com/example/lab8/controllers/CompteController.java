package com.example.lab8.controllers;

import com.example.lab8.entities.Compte;
import com.example.lab8.repositories.CompteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/banque")
public class CompteController {
	private final CompteRepository compteRepository;

	public CompteController(CompteRepository compteRepository) {
		this.compteRepository = compteRepository;
	}

	@GetMapping(value = "/comptes", produces = {"application/json", "application/xml"})
	public List<Compte> getAllComptes() {
		return compteRepository.findAll();
	}

	@GetMapping(value = "/comptes/{id}", produces = {"application/json", "application/xml"})
	public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
		return compteRepository.findById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping(value = "/comptes", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
		Compte saved = compteRepository.save(compte);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(location).body(saved);
	}

	@PutMapping(value = "/comptes/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
	public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte details) {
		return compteRepository.findById(id).map(compte -> {
			compte.setSolde(details.getSolde());
			compte.setDateCreation(details.getDateCreation());
			compte.setType(details.getType());
			return ResponseEntity.ok(compteRepository.save(compte));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/comptes/{id}")
	public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
		return compteRepository.findById(id).map(compte -> {
			compteRepository.delete(compte);
			return ResponseEntity.noContent().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}

package com.hackathon.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.spring.jpa.h2.model.Registry;
import com.hackathon.spring.jpa.h2.repository.RegistryRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RegistryController {

	@Autowired
	RegistryRepository registryRepository;

	@GetMapping("/registries")
	public ResponseEntity<List<Registry>> getAllRegistries(@RequestParam(required = false) String name) {
		try {
			List<Registry> registries = new ArrayList<Registry>();

			if (name == null)
				registryRepository.findAll().forEach(registries::add);
			else
				registryRepository.findBynameContaining(name).forEach(registries::add);

			if (registries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(registries, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/registries/{id}")
	public ResponseEntity<Registry> getRegistryById(@PathVariable("id") long id) {
		Optional<Registry> registryData = registryRepository.findById(id);

		if (registryData.isPresent()) {
			return new ResponseEntity<>(registryData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/registries")
	public ResponseEntity<Registry> createRegistry(@RequestBody Registry registry) {
		try {
			Registry _registry = registryRepository
					.save(new Registry(registry.getName(), registry.getGovtID(), registry.getAddress(),
							registry.isregistered()));
			return new ResponseEntity<>(_registry, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/registries/{id}")
	public ResponseEntity<Registry> updateRegistry(@PathVariable("id") long id, @RequestBody Registry registry) {
		Optional<Registry> registryData = registryRepository.findById(id);

		if (registryData.isPresent()) {
			Registry _registry = registryData.get();
			_registry.setName(registry.getName());
			_registry.setGovtID(registry.getGovtID());
			_registry.setAddress(registry.getAddress());
			_registry.setregistered(registry.isregistered());
			return new ResponseEntity<>(registryRepository.save(_registry), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/registries/{id}")
	public ResponseEntity<HttpStatus> deleteRegistry(@PathVariable("id") long id) {
		try {
			registryRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/registries")
	public ResponseEntity<HttpStatus> deleteAllRegistries() {
		try {
			registryRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/registries/registered")
	public ResponseEntity<List<Registry>> findByregistered() {
		try {
			List<Registry> registries = registryRepository.findByregistered(true);

			if (registries.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(registries, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

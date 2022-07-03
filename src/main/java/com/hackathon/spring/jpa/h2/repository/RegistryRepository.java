package com.hackathon.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.spring.jpa.h2.model.Registry;

public interface RegistryRepository extends JpaRepository<Registry, Long> {
  List<Registry> findByregistered(boolean registered);

  List<Registry> findBynameContaining(String name);
}

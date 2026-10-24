package com.example.vehiclerental.repository;

import com.example.vehiclerental.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeatureRepository extends JpaRepository<Feature, UUID> {
}
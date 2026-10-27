package com.example.vehiclerental.repository;

import com.example.vehiclerental.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, UUID> {
    @Query ("""
        select f from Feature f
        where f.vehicles is empty
    """)
    List<Feature> findAvailableFeatures();
}
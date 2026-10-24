
package com.example.vehiclerental.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.vehiclerental.domain.Feature;
import com.example.vehiclerental.repository.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeatureService {
    @Autowired
    private  FeatureRepository featureRepository;

    
     public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }
    public List<Feature> findAll() {
        return featureRepository.findAll();
    }
    public Optional<Feature> findById(UUID id) {
        return featureRepository.findById(id);
    }
    public Optional<Feature> update(UUID id, Feature newFeature) {

        return featureRepository.findById(id)
                .map(existingFeature -> {

                    existingFeature.setName(newFeature.getName());

                    return featureRepository.save(existingFeature);
                });
    }

    public boolean delete(UUID id) {
        if(!featureRepository.existsById(id)) {
            return false;
        }
        featureRepository.deleteById(id);
        return true;
    }
}

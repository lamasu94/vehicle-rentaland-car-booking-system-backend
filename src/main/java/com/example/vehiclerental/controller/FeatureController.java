package com.example.vehiclerental.controller;

import com.example.vehiclerental.domain.Feature;
import com.example.vehiclerental.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
    @Autowired
    private FeatureService featureService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Feature feature) {

        featureService.save(feature);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Feature saved successfully");
    }
     @GetMapping("/all")
    public ResponseEntity<List<Feature>> findAll() {

        return ResponseEntity.ok(featureService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        return featureService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Feature feature) {

        return featureService.update(id, feature)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        boolean deleted = featureService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Feature deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

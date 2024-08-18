package com.example.Profile_Service.Controller;

import com.example.Profile_Service.Model.Profile;
import com.example.Profile_Service.Service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Integer userId) {
        return profileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Integer userId, @RequestBody Profile profile) {
        try {
            Profile updatedProfile = profileService.updateProfile(userId, profile);
            return ResponseEntity.ok(updatedProfile);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/portfolio")
    public ResponseEntity<Profile> addPortfolioItem(@PathVariable Integer userId, @RequestBody String portfolioItem) {
        try {
            Profile updatedProfile = profileService.addPortfolioItem(userId, portfolioItem);
            return ResponseEntity.ok(updatedProfile);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


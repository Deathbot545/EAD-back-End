package com.example.Profile_Service.Controller;

import com.example.Profile_Service.Model.Profile;
import com.example.Profile_Service.Service.ProfileService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            profile.setUserId(userId); // Set userId, not id
            Profile updatedProfile = profileService.updateProfile(userId, profile);
            return ResponseEntity.ok(updatedProfile);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createProfile")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        try {
            Profile createdProfile = profileService.createProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }


}


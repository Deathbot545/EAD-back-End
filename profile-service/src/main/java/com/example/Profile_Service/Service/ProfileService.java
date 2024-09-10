package com.example.Profile_Service.Service;


import com.example.Profile_Service.Model.Profile;
import com.example.Profile_Service.Model.ProfileRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Profile> getProfileByUserId(Integer userId) {

        return profileRepository.findById(userId);
    }

    public Profile updateProfile(Integer userId, Profile profile) {
        if (!profileRepository.existsByUserId(userId)) {
            throw new EntityNotFoundException("Profile not found for user ID: " + userId);
        }
        profile.setUserId(userId); // Ensure userId is set
        return profileRepository.save(profile);
    }



    public Profile createProfile(Profile profile) {
        // Check if a profile for the user already exists
        if (profileRepository.existsByUserId(profile.getUserId())) {
            throw new EntityExistsException("Profile already exists for user ID: " + profile.getUserId());
        }

        // Save the new profile
        return profileRepository.save(profile);
    }
}

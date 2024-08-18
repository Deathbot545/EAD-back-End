package com.example.Profile_Service.Service;


import com.example.Profile_Service.Model.Profile;
import com.example.Profile_Service.Model.ProfileRepository;
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
        return profileRepository.findByUserId(userId);
    }

    public Profile updateProfile(Integer userId, Profile profile) {
        if (!profileRepository.existsByUserId(userId)) {
            throw new EntityNotFoundException("Profile not found for user ID: " + userId);
        }
        profile.setUserId(userId); // Ensure userId is set
        return profileRepository.save(profile);
    }

    public Profile addPortfolioItem(Integer userId, String portfolioItem) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user ID: " + userId));

        String currentPortfolio = profile.getPortfolio();
        if (currentPortfolio == null || currentPortfolio.isEmpty()) {
            profile.setPortfolio("[" + portfolioItem + "]");
        } else {
            profile.setPortfolio(currentPortfolio.replaceFirst("\\]$", ", " + portfolioItem + "]")); // Ensure valid JSON format
        }

        return profileRepository.save(profile);
    }
}

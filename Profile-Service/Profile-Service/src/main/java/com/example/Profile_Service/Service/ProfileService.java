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

    public Optional<
                Profile> getProfileByUserId(UUID userId) {
        return profileRepository.findById(userId.hashCode());
    }

    public Profile updateProfile(UUID userId, Profile profile) {
        int userIdHash = userId.hashCode();
        if (!profileRepository.existsById(userIdHash)) {
            throw new EntityNotFoundException("Profile not found for user ID: " + userId);
        }
        profile.setUserId(userId);
        return profileRepository.save(profile);
    }

    public Profile addPortfolioItem(UUID userId, String portfolioItem) {
        Profile profile = profileRepository.findById(userId.hashCode())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user ID: " + userId));

        String currentPortfolio = profile.getPortfolio();
        if (currentPortfolio == null || currentPortfolio.isEmpty()) {
            profile.setPortfolio("[" + portfolioItem + "]");
        } else {
            profile.setPortfolio(currentPortfolio.substring(0, currentPortfolio.length() - 1) + ", " + portfolioItem + "]");
        }

        return profileRepository.save(profile);
    }
}

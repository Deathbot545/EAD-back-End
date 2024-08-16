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
        return profileRepository.findById(userId);
    }

    public Profile updateProfile(Integer userId, Profile profile) {
        if (!profileRepository.existsById(userId)) {
            throw new EntityNotFoundException("Profile not found for user ID: " + userId);
        }
        profile.setId(userId);
        return profileRepository.save(profile);
    }

    public Profile addPortfolioItem(Integer userId, String portfolioItem) {
        Profile profile = profileRepository.findById(userId)
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

package com.example.Profile_Service.Model;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository
        extends JpaRepository<Profile, Integer> {
}

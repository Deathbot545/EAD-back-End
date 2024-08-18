package com.example.Profile_Service.Model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByUserId(Integer userId);

    boolean existsByUserId(Integer userId);
}

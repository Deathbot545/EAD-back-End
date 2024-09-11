package com.example.List_Service_Service.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListServiceRepository extends JpaRepository<ListService, Integer> {
    // Custom query methods can be added here if needed
    List<ListService> findByFreelancerId(Integer freelancerId);
}

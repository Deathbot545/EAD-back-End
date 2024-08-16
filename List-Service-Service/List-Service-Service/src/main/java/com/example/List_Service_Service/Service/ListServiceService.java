package com.example.List_Service_Service.Service;


import com.example.List_Service_Service.Model.ListService;
import com.example.List_Service_Service.Model.ListServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class ListServiceService {

    @Autowired
    private ListServiceRepository listServiceRepository;

    public ListService createListService(ListService listService) {
        listService.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        listService.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return listServiceRepository.save(listService);
    }

    public Optional<ListService> getListServiceById(Integer id) {
        return listServiceRepository.findById(id);
    }

    public ListService updateListService(Integer id, ListService listService) {
        if (!listServiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Service not found with id: " + id);
        }
        listService.setId(id);
        listService.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return listServiceRepository.save(listService);
    }

    public void deleteListService(Integer id) {
        if (!listServiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Service not found with id: " + id);
        }
        listServiceRepository.deleteById(id);
    }

    public List<ListService> listListServices(String category, BigDecimal minPrice, BigDecimal maxPrice) {
        // Custom query methods can be used to filter by category and price range
        // For example, using Spring Data JPA query methods or a custom query
        // This example assumes a custom query method might be defined in ListServiceRepository
        return listServiceRepository.findAll(); // Modify this as needed for filtering
    }
}
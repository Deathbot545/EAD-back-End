package com.example.List_Service_Service.Controller;

import com.example.List_Service_Service.Model.ListService;
import com.example.List_Service_Service.Service.ListServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class ListServiceController {

    @Autowired
    private ListServiceService listServiceService;

    @PostMapping
    public ResponseEntity<ListService> createListService(@RequestBody ListService listService) {
        ListService createdService = listServiceService.createListService(listService);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListService> getListServiceById(@PathVariable Integer id) {
        Optional<ListService> service = listServiceService.getListServiceById(id);
        return service.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListService> updateListService(@PathVariable Integer id, @RequestBody ListService listService) {
        ListService updatedService = listServiceService.updateListService(id, listService);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListService(@PathVariable Integer id) {
        listServiceService.deleteListService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ListService>> listListServices(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<ListService> services = listServiceService.listListServices(category, minPrice, maxPrice);
        return ResponseEntity.ok(services);
    }
}

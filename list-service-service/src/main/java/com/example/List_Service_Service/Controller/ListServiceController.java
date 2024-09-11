package com.example.List_Service_Service.Controller;

import com.example.List_Service_Service.Model.ListService;
import com.example.List_Service_Service.Service.ListServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
public class ListServiceController {

    @Autowired
    private ListServiceService listServiceService;

    @PostMapping("/create")
    public ResponseEntity<?> createService(
            @RequestParam("title") String title,
            @RequestParam("miniDescription") String miniDescription,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("price") BigDecimal price,
            @RequestParam("coverImage") MultipartFile coverImage,
            @RequestParam("freelancerId") Integer freelancerId) { // Add freelancerId here
        try {
            ListService service = new ListService();
            service.setTitle(title);
            service.setMiniDescription(miniDescription);
            service.setDescription(description);
            service.setCategory(category);
            service.setPrice(price);
            service.setCoverImage(coverImage.getBytes()); // Convert file to byte array
            service.setFreelancerId(freelancerId); // Set the freelancerId
            service.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            service.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Save the service using your service layer
            ListService savedService = listServiceService.createListService(service);
            return new ResponseEntity<>(savedService, HttpStatus.CREATED);
        } catch (IOException e) {
            // Log the stack trace for debugging
            e.printStackTrace();
            return new ResponseEntity<>("Error processing the cover image", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Log the full stack trace to understand the cause
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/services")
    public ResponseEntity<List<ListService>> listListServices(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<ListService> services = listServiceService.listListServices(category, minPrice, maxPrice);
        return ResponseEntity.ok(services);
    }
    @GetMapping("/services/freelancer/{freelancerId}")
    public ResponseEntity<List<ListService>> getServicesByFreelancerId(@PathVariable Integer freelancerId) {
        List<ListService> services = listServiceService.getServicesByFreelancerId(freelancerId);
        if (services.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
}

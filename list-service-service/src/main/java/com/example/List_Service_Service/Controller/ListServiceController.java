package com.example.List_Service_Service.Controller;

import com.example.List_Service_Service.Model.ListService;
import com.example.List_Service_Service.Service.ListServiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
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
            HttpSession session) { // Ensure HttpSession is passed to get session data
        try {
            // Extracting freelancerId from the session
            // Assuming 'profile' is stored in the session as a JSON string and contains the freelancerId
            String profileJson = (String) session.getAttribute("profile");
            if (profileJson == null) {
                return new ResponseEntity<>("No profile found in session", HttpStatus.UNAUTHORIZED);
            }

            // Parse the profile JSON string to extract the freelancerId
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> profile = objectMapper.readValue(profileJson, Map.class);
            Integer freelancerId = (Integer) profile.get("userId");

            if (freelancerId == null) {
                return new ResponseEntity<>("Freelancer ID not found in session", HttpStatus.BAD_REQUEST);
            }

            // Create and set up the ListService object
            ListService service = new ListService();
            service.setFreelancerId(freelancerId); // Set freelancerId
            service.setTitle(title);
            service.setMiniDescription(miniDescription);
            service.setDescription(description);
            service.setCategory(category);
            service.setPrice(price);
            service.setCoverImage(coverImage.getBytes());
            service.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            service.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Save the service using your service layer
            ListService savedService = listServiceService.createListService(service);
            return new ResponseEntity<>(savedService, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing the cover image", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
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

    @GetMapping
    public ResponseEntity<List<ListService>> listListServices(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<ListService> services = listServiceService.listListServices(category, minPrice, maxPrice);
        return ResponseEntity.ok(services);
    }
}

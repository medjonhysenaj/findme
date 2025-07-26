package com.automatic.apartament.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.automatic.apartament.dto.PlaceDTO;
import com.automatic.apartament.entity.Categories;
import com.automatic.apartament.entity.Groups;
import com.automatic.apartament.entity.Place;
import com.automatic.apartament.service.ApartmentService;

@RestController
@RequestMapping("/api") //

public class MapController {

    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/map")
    public String redirectToMap() {
        return "redirect:/homemapping/index.html";
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, Object>> chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");

        // TODO: Implement routing logic here or return dummy route for testing
        Map<String, Object> response = new HashMap<>();
        response.put("route", List.of(
            Map.of("name", "Bar1", "lat", 41.3275, "lng", 19.8189),
            Map.of("name", "Restaurant1", "lat", 41.3251, "lng", 19.8204),
            Map.of("name", "Park1", "lat", 41.3243, "lng", 19.8162)
        ));

        return ResponseEntity.ok(response);
    }
    
    // ====== CATEGORIES ======

@GetMapping("/categories")
public ResponseEntity<?> getCategories() {
    List<Categories> list = apartmentService.getAllCategories();

    if (list == null || list.isEmpty()) {
        System.out.println("⚠️ No categories found");
        return ResponseEntity.ok(List.of()); // send empty JSON array
    }

    System.out.println("✅ Categories found: " + list.size());
    return ResponseEntity.ok(list);
}

    @GetMapping("/places")
    public List<Place> getAllPlaces() {
        return apartmentService.getAllPlaces(); // returns data from DB
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable int id, @RequestBody Place updatedPlace) {
        Place result = apartmentService.updatePlace(id, updatedPlace);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }
    
    @PostMapping("/categories")
    public void addCategory(@RequestBody Categories category) {
        apartmentService.addCategory(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable int id, @RequestBody Categories updatedCategory) {
        Categories result = apartmentService.updateCategory(id, updatedCategory);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        boolean deleted = apartmentService.deleteCategory(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // ====== GROUPS ======

    @PostMapping("/groups")
    public ResponseEntity<?> addGroup(
            @RequestParam("name") String name,
            @RequestParam("categoryId") int categoryId,
            @RequestParam(value = "icon", required = false) MultipartFile iconFile) {

        String iconPath = null;
        if (iconFile != null && !iconFile.isEmpty()) {
            try {
                // Correct way to define and create the upload directory
                Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads", "icons");
                Files.createDirectories(uploadDir);

                // Generate unique filename and full path
                String filename = System.currentTimeMillis() + "_" + iconFile.getOriginalFilename();
                Path filePath = uploadDir.resolve(filename);

                // Save file
                iconFile.transferTo(filePath.toFile());

                // Store relative path that you will use on the frontend
                iconPath = "/icons/" + filename;

            } catch (Exception e) {
                return ResponseEntity.status(500).body("Icon upload failed: " + e.getMessage());
            }
        }

        Groups group = new Groups();
        group.setName(name);
        group.setCategoryId(categoryId);
        group.setIconPath(iconPath);

        apartmentService.addGroup(group);

        return ResponseEntity.ok("Group created");
    }

    @GetMapping("/groups")
    public List<Groups> getGroups() {
        return apartmentService.getAllGroups();
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Groups> updateGroup(@PathVariable int id, @RequestBody Groups updatedGroup) {
        Groups result = apartmentService.updateGroup(id, updatedGroup);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id) {
        boolean deleted = apartmentService.deleteGroup(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable int id) {
        boolean deleted = apartmentService.deletePlace(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/places")
    public ResponseEntity<?> addPlace(
        @RequestParam("name") String name,
        @RequestParam("description") String description,
        @RequestParam("latitude") double latitude,
        @RequestParam("longitude") double longitude,
        @RequestParam("groupId") int groupId,
        @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        String iconPath = null;

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads", "places");
                Files.createDirectories(uploadDir);

                String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path filePath = uploadDir.resolve(filename);
                imageFile.transferTo(filePath.toFile());

                iconPath = "/places/" + filename;
            }

            Place place = new Place(name, description, latitude, longitude, groupId, iconPath);
            apartmentService.savePlace(place);

            return ResponseEntity.ok("Place added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding place: " + e.getMessage());
        }
    }

    @PostMapping("/buildRoute")
    public ResponseEntity<?> buildRoute(@RequestBody List<String> groupNames) {
        try {
            System.out.println("📥 Received group names: " + groupNames);

            List<Place> places = apartmentService.buildRoute(groupNames);

            List<PlaceDTO> route = places.stream()
                .map(p -> new PlaceDTO(p.getName(), p.getLatitude(), p.getLongitude()))
                .toList();

            return ResponseEntity.ok(route);
        } catch (RuntimeException e) {
            System.err.println("❌ Error while building route: " + e.getMessage());
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Unexpected server error.");
        }
    }

}

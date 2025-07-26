package com.automatic.apartament.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automatic.apartament.entity.Categories;
import com.automatic.apartament.entity.Groups;
import com.automatic.apartament.entity.Place;
import com.automatic.apartament.repository.CategoriesRepository;
import com.automatic.apartament.repository.GroupsRepository;
import com.automatic.apartament.repository.PlaceRepository;
import com.automatic.apartament.util.Haversine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApartmentService {

	@Autowired CategoriesRepository catrep;
	@Autowired GroupsRepository     groupsrep;
	@Autowired PlaceRepository      placerep;

	private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);

	public void addCategory(Categories cat) {
		catrep.save(cat);
	}
	
	public List<Categories>getAllCategories(){
		return catrep.findAll();
	}
	
	public boolean deleteCategory(int id) {
	    if (catrep.existsById(id)) {
	        catrep.deleteById(id);
	        return true;
	    }
	    return false;
	}
	
	public Categories updateCategory(int id, Categories updatedCategory) {
	    return catrep.findById(id).map(existing -> {
	        existing.setName(updatedCategory.getName());
	        // Add other fields here if needed
	        return catrep.save(existing);
	    }).orElse(null);
	}
	
	public void addGroup(Groups group) {
		groupsrep.save(group);
	}	
	
	public List<Groups> getAllGroups() {
	    return groupsrep.findAll();
	}

	public Groups updateGroup(int id, Groups updatedGroup) {
	    return groupsrep.findById(id).map(existing -> {
	        existing.setName(updatedGroup.getName());
	        existing.setCategoryId(updatedGroup.getCategoryId());
	        return groupsrep.save(existing);
	    }).orElse(null);
	}

	public boolean deleteGroup(int id) {
	    Optional<Groups> optionalGroup = groupsrep.findById(id);
	    if (optionalGroup.isPresent()) {
	        Groups group = optionalGroup.get();

	        // Delete icon file if it exists
	        String iconPath = group.getIconPath(); // e.g. "/icons/175319914712_castle.png"
	        if (iconPath != null && !iconPath.trim().isEmpty()) {
	            try {
	                // Remove leading "/" if present
	                String cleanedPath = iconPath.startsWith("/") ? iconPath.substring(1) : iconPath;

	                // Build the absolute path based on your project root and 'uploads' folder
	                Path iconFullPath = Paths.get(System.getProperty("user.dir"), "uploads", cleanedPath);
	                File file = iconFullPath.toFile();

	                System.out.println("🔍 Trying to delete icon file: " + file.getAbsolutePath());

	                if (file.exists()) {
	                    boolean deleted = file.delete();
	                    System.out.println(deleted ? "✅ Icon deleted!" : "❌ Failed to delete icon.");
	                } else {
	                    System.out.println("⚠️ File does not exist: " + file.getAbsolutePath());
	                }
	            } catch (Exception e) {
	                System.out.println("❌ Error deleting icon: " + e.getMessage());
	            }
	        }

	        // Delete group from DB
	        groupsrep.deleteById(id);
	        System.out.println("✅ Group deleted from DB with ID: " + id);
	        return true;
	    }

	    System.out.println("❌ Group not found with ID: " + id);
	    return false;
	}





    public void savePlace(Place place) {
    	placerep.save(place);
    }

	public List<Place>getAllPlaces(){
		return placerep.findAll();
	}


	public boolean deletePlace(int id) {
	    Optional<Place> optionalPlace = placerep.findById(id);
	    if (optionalPlace.isPresent()) {
	        Place place = optionalPlace.get();
	        String iconPath = place.getIconPath(); // e.g., "/places/xyz.jpg"

	        // Delete the image file if it exists
	        if (iconPath != null && !iconPath.isBlank()) {
	            try {
	                // Remove leading "/" if present
	                String cleanedPath = iconPath.startsWith("/") ? iconPath.substring(1) : iconPath;
	                
	                // Build the absolute path based on project root
	                Path path = Paths.get(System.getProperty("user.dir"), "uploads", cleanedPath);
	                
	                // Delete file if it exists
	                Files.deleteIfExists(path);
	            } catch (IOException e) {
	                e.printStackTrace(); // Log error but continue deletion
	            }
	        }

	        // Delete the database record
	        placerep.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}

	public Place updatePlace(int id, Place updatedPlace) {
	    Optional<Place> existing = placerep.findById(id);
	    if (existing.isPresent()) {
	        Place place = existing.get();
	        place.setName(updatedPlace.getName());
	        place.setDescription(updatedPlace.getDescription());
	        place.setLatitude(updatedPlace.getLatitude());
	        place.setLongitude(updatedPlace.getLongitude());
	        // Optional: update iconPath or groupId if needed
	        return placerep.save(place);
	    }
	    return null;
	}

    // Start point: Apartment coordinates
    private static final double START_LAT = 41.3275;
    private static final double START_LNG = 19.8189;

    public List<Place> buildRoute(List<String> groupNames) {
        List<Place> route = new ArrayList<>();
        double currentLat = START_LAT;
        double currentLng = START_LNG;

        for (String groupName : groupNames) {
            Groups group = groupsrep.findByNameContainingIgnoreCase(groupName)
                    .orElseThrow(() -> new RuntimeException("Group not found: " + groupName));

            List<Place> places = placerep.findByGroupId(group.getId());
            Place nearest = findNearest(currentLat, currentLng, places);

            if (nearest != null) {
                route.add(nearest);
                currentLat = nearest.getLatitude();
                currentLng = nearest.getLongitude();
            }
        }

        return route;
    }


    private Place findNearest(double fromLat, double fromLng, List<Place> places) {
        return places.stream()
                .min(Comparator.comparingDouble(p -> Haversine.distance(fromLat, fromLng, p.getLatitude(), p.getLongitude())))
                .orElse(null);
    }

}

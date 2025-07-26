package com.automatic.apartament.repository;

import com.automatic.apartament.entity.Place;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    
	List<Place> findByGroupId(int groupId); // ✅ THIS is what you need
}

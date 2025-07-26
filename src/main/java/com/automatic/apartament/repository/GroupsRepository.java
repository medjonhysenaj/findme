package com.automatic.apartament.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automatic.apartament.entity.Groups;

public interface GroupsRepository extends JpaRepository<Groups, Integer>{

	Optional<Groups> findByNameContainingIgnoreCase(String name); // 👈 this is necessary for buildRoute()
}

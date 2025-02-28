package com.realtime_vehicles.users.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realtime_vehicles.users.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

}

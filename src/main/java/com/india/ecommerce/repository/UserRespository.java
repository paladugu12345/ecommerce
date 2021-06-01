package com.india.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.ecommerce.entity.User;

public interface UserRespository extends JpaRepository<User,Long> {

	Optional<User> findByUserId(Long userId);
}

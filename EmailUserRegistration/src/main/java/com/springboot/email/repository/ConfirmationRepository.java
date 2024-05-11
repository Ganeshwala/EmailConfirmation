package com.springboot.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.email.domain.Confirmation;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

	Confirmation findByConfirmKey(String token);
}

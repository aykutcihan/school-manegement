package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.Dean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeanRepository extends JpaRepository<Dean, Long> {
    Dean findByUsernameEquals(String username);
}

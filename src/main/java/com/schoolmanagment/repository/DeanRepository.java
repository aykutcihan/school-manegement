package com.schoolmanagment.repository;

import com.schoolmanagment.entity.concretes.Dean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeanRepository extends JpaRepository<Dean, Long> {
    Dean findByUsernameEquals(String username);

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);
}

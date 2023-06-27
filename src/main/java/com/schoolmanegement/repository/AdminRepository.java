package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByUsernameEquals(String username);

}

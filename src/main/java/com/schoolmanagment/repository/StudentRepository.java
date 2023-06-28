package com.schoolmanagment.repository;

import com.schoolmanagment.entity.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository {

    Student findByUsernameEquals(String username);

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);

    boolean existsByEmail(String email);
}

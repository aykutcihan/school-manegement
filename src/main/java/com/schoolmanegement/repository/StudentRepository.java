package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository {

    Student findByUsernameEquals(String username);
}

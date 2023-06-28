package com.schoolmanagment.repository;

import com.schoolmanagment.entity.concretes.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUsernameEquals(String username);

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);

    boolean existsByEmail(String email);
}

package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUsernameEquals(String username);

}

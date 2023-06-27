package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable);

    Page<ContactMessage> findBySubject(String subject, Pageable pageable);

    boolean existsByEmailEqualsAndDateEquals(String email, LocalDate date);
}

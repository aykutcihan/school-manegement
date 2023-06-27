package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.ContactMessage;
import com.schoolmanegement.payload.response.ContactMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    Page<ContactMessageResponse> findByEmailEquals(String email, Pageable pageable);

    Page<ContactMessageResponse> findBySubject(String subject, Pageable pageable);
}

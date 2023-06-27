package com.schoolmanegement.repository;

import com.schoolmanegement.entity.concretes.ViceDean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViceDeanRepository extends JpaRepository<ViceDean, Long> {
    ViceDean findByUsernameEquals(String username);
}

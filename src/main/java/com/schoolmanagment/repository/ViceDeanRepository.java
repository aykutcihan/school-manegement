package com.schoolmanagment.repository;

import com.schoolmanagment.entity.concretes.ViceDean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViceDeanRepository extends JpaRepository<ViceDean, Long> {
    ViceDean findByUsernameEquals(String username);

    boolean existsByUsername(String username);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phone);
}

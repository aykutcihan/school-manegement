package com.schoolmanagment.repository;

import com.schoolmanagment.entity.concretes.UserRole;
import com.schoolmanagment.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("SELECT r FROM UserROLE r WHERE r.roleType=?1")
    Optional<UserRole> findByEnumRoleEquals(RoleType roleType);


    @Query("SELECT (count (r)>0 FROM UserRole r WHERE r.roleType= ?1")
    boolean existsByEnumRoleEquals(RoleType roleType);
}

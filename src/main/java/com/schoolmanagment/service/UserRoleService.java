package com.schoolmanagment.service;

import com.schoolmanagment.entity.concretes.UserRole;
import com.schoolmanagment.entity.enums.RoleType;
import com.schoolmanagment.exception.ConflictException;
import com.schoolmanagment.repository.UserRoleRepository;
import com.schoolmanagment.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole(RoleType roleType) {
        return userRoleRepository.findByEnumRoleEquals(roleType)
                .orElseThrow(() -> new ConflictException(Messages.ROLE_NOT_FOUND));
    }

        public List<UserRole> getAllUserRole() {
            return userRoleRepository.findAll();
        }
        public UserRole save (RoleType roleType){
            if(userRoleRepository.existsByEnumRoleEquals(roleType)){
                throw new ConflictException(Messages.ROLE_ALREADY_EXIST);
            }


        UserRole userRole = UserRole.builder()
                .roleType(roleType)
                .build();
   return userRoleRepository.save(userRole);
    }
}

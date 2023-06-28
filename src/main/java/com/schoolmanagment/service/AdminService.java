package com.schoolmanagment.service;

import com.schoolmanagment.entity.concretes.Admin;
import com.schoolmanagment.entity.enums.RoleType;
import com.schoolmanagment.exception.ConflictException;
import com.schoolmanagment.exception.ResourceNotFoundException;
import com.schoolmanagment.payload.mappers.AdminDto;
import com.schoolmanagment.payload.request.AdminRequest;
import com.schoolmanagment.payload.response.AdminResponse;
import com.schoolmanagment.payload.response.ResponseMessage;
import com.schoolmanagment.repository.AdminRepository;
import com.schoolmanagment.utils.Messages;
import com.schoolmanagment.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminDto adminDto;
    private final  UserRoleService userRoleService;

    private final ServiceHelpers serviceHelpers;
    private final Admin admin;


    public ResponseMessage<AdminResponse> save(AdminRequest adminRequest) {

        Admin admin = adminDto.mapAdminRequestToAdmin(adminRequest);
        admin.setBuilt_in(false);
        if(Objects.equals(adminRequest.getUsername(),"Admin")){
            admin.setBuilt_in(true);
        }
        admin.setUserRole(userRoleService.getUserRole(RoleType.ADMIN));
        Admin savedAdmin = adminRepository.save(admin);

        return ResponseMessage.<AdminResponse>builder()
                .message("Admin Saved")
                .httpStatus(HttpStatus.CREATED)
                .object(adminDto.mapAdminToAdminResponse(savedAdmin))
                .build();

    }


    public Page<AdminResponse> getAll(int page, int size, String sort, String type) {
         Pageable pageable = serviceHelpers.getPageableWithProperties(page, size, sort, type);
         return adminRepository.findAll(pageable).map(adminDto::mapAdminToAdminResponse);

    }

    public ResponseMessage<?> deleteAdminById(Long adminId) {
        isAdminExist(adminId);
        if (admin.isBuilt_in()) {
            throw new ConflictException("Built-in Admin cannot be deleted");
        }
        adminRepository.deleteById(adminId);

        return ResponseMessage.builder()
                .message("Admin deleted successfully")
                .httpStatus(HttpStatus.OK)
                .build();

    }

    private Admin isAdminExist(Long id) {
        return adminRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE)));
    }


    public long countAllAdmins() {
        return adminRepository.count();
    }
}

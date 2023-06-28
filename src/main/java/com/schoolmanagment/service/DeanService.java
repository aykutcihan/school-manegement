package com.schoolmanagment.service;

import com.schoolmanagment.entity.concretes.Dean;
import com.schoolmanagment.entity.enums.RoleType;
import com.schoolmanagment.exception.ResourceNotFoundException;
import com.schoolmanagment.payload.mappers.DeanDto;
import com.schoolmanagment.payload.request.DeanRequest;
import com.schoolmanagment.payload.response.DeanResponse;
import com.schoolmanagment.payload.response.ResponseMessage;
import com.schoolmanagment.repository.DeanRepository;
import com.schoolmanagment.utils.CheckParameterUpdateMethod;
import com.schoolmanagment.utils.Messages;
import com.schoolmanagment.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeanService {
    private final DeanRepository deanRepository;
    private final DeanDto deanDto;
    private final ServiceHelpers serviceHelpers;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final CheckParameterUpdateMethod checkParameterUpdateMethod;

    public ResponseMessage<DeanResponse> save(DeanRequest deanRequest) {
        serviceHelpers.checkDuplicate(deanRequest.getUsername(), deanRequest.getSsn(), deanRequest.getPhoneNumber());
        Dean dean = deanDto.mapDeanRequestToDean(deanRequest);
        dean.setUserRole(userRoleService.getUserRole(RoleType.MANAGER));
        dean.setPassword(passwordEncoder.encode(dean.getPassword()));

        Dean savedDean = deanRepository.save(dean);

        return ResponseMessage.<DeanResponse>builder()
                .message("Dean Saved")
                .httpStatus(HttpStatus.CREATED)
                .object(deanDto.mapDeanToDeanResponse(savedDean))
                .build();
    }

    public ResponseMessage<DeanResponse> update(DeanRequest deanRequest, Long deanId) {
        Optional<Dean> dean = isDeanExist(deanId);

        if (!CheckParameterUpdateMethod.checkUniqueProperties(dean.get(), deanRequest)) {
            serviceHelpers.checkDuplicate(deanRequest.getUsername(),
                    deanRequest.getPhoneNumber(),
                    deanRequest.getSsn());
        }

        Dean updateDean = deanDto.mapDeanRequestToUpdatedDean(deanRequest, deanId);
        updateDean.setPassword(passwordEncoder.encode(deanRequest.getPassword()));
        Dean savedDean = deanRepository.save(updateDean);

        return ResponseMessage.<DeanResponse>builder()
                .message("Dean Updated Successfully")
                .httpStatus(HttpStatus.OK)
                .object(deanDto.mapDeanToDeanResponse(savedDean))
                .build();

    }
    public Optional<Dean> isDeanExist(Long deanId) {
        Optional<Dean> dean = deanRepository.findById(deanId);
        if (!dean.isPresent()) {
            throw new ResourceNotFoundException(String.format(Messages.NOT_FOUND_USER_MESSAGE, deanId));
        } else {
            return dean;
        }
    }

    public ResponseMessage<?> deleteDeanById(Long deanId) {
        isDeanExist(deanId);

        deanRepository.deleteById(deanId);

        return ResponseMessage.builder()
                .message("Dean deleted")
                .httpStatus(HttpStatus.OK)
                .build();


    }

    public ResponseMessage<DeanResponse> getDeanById(Long deanId) {
        return ResponseMessage.<DeanResponse>builder()
                .message("Dean successfully")
                .httpStatus(HttpStatus.OK)
                .object(deanDto.mapDeanToDeanResponse(isDeanExist(deanId).get()))
                .build();
    }

    public List<DeanResponse> getAllDeans() {
        return deanRepository.findAll()
                .stream()
                .map(deanDto::mapDeanToDeanResponse)
                .collect(Collectors.toList());

    }

    public Page<DeanResponse> getAllDeansByPage(int page, int size, String sort, String type) {
        Pageable pageable = serviceHelpers.getPageableWithProperties(page, size, sort, type);
        return deanRepository.findAll(pageable).map(deanDto::mapDeanToDeanResponse);

    }
}
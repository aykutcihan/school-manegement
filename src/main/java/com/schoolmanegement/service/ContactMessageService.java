package com.schoolmanegement.service;

import com.schoolmanegement.entity.concretes.ContactMessage;
import com.schoolmanegement.payload.mappers.ContactMessageDto;
import com.schoolmanegement.payload.request.ContactMessageRequest;
import com.schoolmanegement.payload.response.ContactMessageResponse;
import com.schoolmanegement.payload.response.ResponseMessage;
import com.schoolmanegement.repository.ContactMessageRepository;
import com.schoolmanegement.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageDto contactMessageDto;
    private final ServiceHelpers serviceHelpers;


    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {

//        TODO business logic

        ContactMessage contactMessage = contactMessageDto.mapContactMessageRequestToContactMessage(contactMessageRequest);
        ContactMessage savedContactMessage = contactMessageRepository.save(contactMessage);


        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact Message Created Successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(contactMessageDto.mapContactMessageToContactMessageResponse(savedContactMessage))
                .build();
    }

    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if(Objects.equals(type, "desc")){
            pageable= PageRequest.of(page, size,Sort.by(sort).descending());
        }

        return contactMessageRepository.findAll(pageable).map(contactMessageDto::mapContactMessageToContactMessageResponse);
    }
/*
    public Page<ContactMessageResponse> searchByEmail(String email,int page, int size, String sort, String type) {

        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);

        return contactMessageRepository.findByEmailEquals(email, pageable).map(contactMessageDto::mapContactMessageToContactMessageResponse);


    }

    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {

        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);

        return contactMessageRepository.findBySubject (subject, pageable).map(contactMessageDto::mapContactMessageToContactMessageResponse);
    }

 */
}
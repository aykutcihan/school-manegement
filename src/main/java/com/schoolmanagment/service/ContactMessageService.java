package com.schoolmanagment.service;

import com.schoolmanagment.entity.concretes.ContactMessage;
import com.schoolmanagment.exception.ConflictException;
import com.schoolmanagment.payload.mappers.ContactMessageDto;
import com.schoolmanagment.payload.request.ContactMessageRequest;
import com.schoolmanagment.payload.response.ContactMessageResponse;
import com.schoolmanagment.payload.response.ResponseMessage;
import com.schoolmanagment.repository.ContactMessageRepository;
import com.schoolmanagment.utils.Messages;
import com.schoolmanagment.utils.ServiceHelpers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageDto contactMessageDto;
    private final ServiceHelpers serviceHelpers;


    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {


        boolean isSameMessageWithSameEmailForToday =
                contactMessageRepository.existsByEmailEqualsAndDateEquals(contactMessageRequest.getEmail(), LocalDate.now());
        if (isSameMessageWithSameEmailForToday) {
            throw new ConflictException(Messages.ALREADY_SEND_A_MESSAGE_TODAY);
        }


        ContactMessage contactMessage = contactMessageDto.mapContactMessageRequestToContactMessage(contactMessageRequest);
        ContactMessage savedContactMessage = contactMessageRepository.save(contactMessage);


        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Contact Message Created Successfully")
                .httpStatus(HttpStatus.CREATED)
                .object(contactMessageDto.mapContactMessageToContactMessageResponse(savedContactMessage))
                .build();
    }

    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {
        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);

        return contactMessageRepository.findAll(pageable)
                .map(contactMessageDto::mapContactMessageToContactMessageResponse);
    }

    public Page<ContactMessageResponse> searchByEmail(String email,int page, int size, String sort, String type) {

        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);

        return contactMessageRepository.findByEmailEquals(email, pageable)
                .map(contactMessageDto::mapContactMessageToContactMessageResponse);


    }

    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {

        Pageable pageable = serviceHelpers.getPageableWithProperties(page,size,sort,type);

        return contactMessageRepository.findBySubject (subject, pageable).map(contactMessageDto::mapContactMessageToContactMessageResponse);
    }


}

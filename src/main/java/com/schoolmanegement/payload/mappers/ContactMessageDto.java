package com.schoolmanegement.payload.mappers;

import com.schoolmanegement.entity.concretes.ContactMessage;
import com.schoolmanegement.payload.request.ContactMessageRequest;
import com.schoolmanegement.payload.response.ContactMessageResponse;

import java.time.LocalDate;

public class ContactMessageDto {

    public ContactMessageResponse mapContactMessageToContactMessageResponse (ContactMessage contactMessage){

        return ContactMessageResponse.builder()
                .name(contactMessage.getName())
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .email(contactMessage.getEmail())
                .date(contactMessage.getDate())
                .build();
    }



    public ContactMessage mapContactMessageRequestToContactMessage (ContactMessageRequest contactMessageRequest){

        return ContactMessage.builder()
                .name(contactMessageRequest.getName())
                .subject(contactMessageRequest.getSubject())
                .message(contactMessageRequest.getMessage())
                .email(contactMessageRequest.getEmail())
                .date(LocalDate.now())
                .build();
    }




}

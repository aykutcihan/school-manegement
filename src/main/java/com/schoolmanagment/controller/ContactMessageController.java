package com.schoolmanagment.controller;

import com.schoolmanagment.payload.request.ContactMessageRequest;
import com.schoolmanagment.payload.response.ContactMessageResponse;
import com.schoolmanagment.payload.response.ResponseMessage;
import com.schoolmanagment.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseMessage<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest) {
        return contactMessageService.save(contactMessageRequest);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.getAll(page, size, sort, type);
    }

    @GetMapping("/searchByEmail")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public Page<ContactMessageResponse> searchByEmail (
            @RequestParam(value = "email") String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type ) {
        return contactMessageService.searchByEmail(email,page, size, sort, type);
    }

    @GetMapping("/searchBySubject")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public Page<ContactMessageResponse> searchBySubject (
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "date") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type ) {
        return contactMessageService.searchBySubject(subject,page, size, sort, type);
    }



}

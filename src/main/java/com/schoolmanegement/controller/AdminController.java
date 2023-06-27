package com.schoolmanegement.controller;

import com.schoolmanegement.payload.request.AdminRequest;
import com.schoolmanegement.payload.response.AdminResponse;
import com.schoolmanegement.payload.response.ResponseMessage;
import com.schoolmanegement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN)")
    public ResponseMessage<AdminResponse> save (@RequestBody @Valid AdminRequest adminRequest){
        return adminService.save(adminRequest);
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ADMIN') ")
    public Page<AdminResponse> getAll (
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort",defaultValue = "name") String sort,
            @RequestParam(value = "type",defaultValue = "desc") String type
    ){
        return adminService.getAll(page,size,sort,type);

    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN)")
    public ResponseMessage<?> deleteAdminById(@PathVariable Long id){
        return adminService.deleteAdminById(id);
    }


}

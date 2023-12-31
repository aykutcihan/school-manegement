package com.schoolmanagment.controller;

import com.schoolmanagment.payload.request.DeanRequest;
import com.schoolmanagment.payload.response.DeanResponse;
import com.schoolmanagment.payload.response.ResponseMessage;
import com.schoolmanagment.service.DeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("dean")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
public class DeanController {

    private final DeanService deanService;

    @PostMapping("/update/{userId}")
    public ResponseMessage<DeanResponse> save(@RequestBody @Valid DeanRequest deanRequest){
        return deanService.save(deanRequest);
    }
    @PutMapping("/update/{userId}")
    public ResponseMessage<DeanResponse> update (@RequestBody @Valid DeanRequest deanRequest,
                                                 @PathVariable Long userId){
        return deanService.update(deanRequest,userId);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseMessage<?> deleteDeanById (@PathVariable Long userId){
        return deanService.deleteDeanById(userId);
    }

    @GetMapping("/getManagerById/{userId")
    public ResponseMessage<DeanResponse> getDeanById (@PathVariable Long userId){
        return deanService.getDeanById(userId);
    }

    @GetMapping("/getAll")
    public List<DeanResponse> getAllDean(){
        return deanService.getAllDeans();
    }

    public Page<DeanResponse> getAllDeansByPage(
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(defaultValue = "desc",value = "type") String type
    ){
        return deanService.getAllDeansByPage(page, size, sort, type);
    }



}

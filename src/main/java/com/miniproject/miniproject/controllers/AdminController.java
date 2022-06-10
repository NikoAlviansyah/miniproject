package com.miniproject.miniproject.controllers;

import com.miniproject.miniproject.dtos.admin.*;
import com.miniproject.miniproject.responses.RestResponse;
import com.miniproject.miniproject.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<AdminDto>>> getAllAdmin() {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllAdmin(),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<RestResponse<List<AdminDto>>> getAllAdminByName(@PathVariable String name) {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllAdminByName(name),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<AdminDto>> createAdmin(@RequestBody CreateAdminDto newAdmin) {
        return new ResponseEntity<>(
                new RestResponse<>(service.createAdmin(newAdmin),
                        "Create new admin successfully.",
                        "201"),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RestResponse<AdminDto>> updateAdmin(@RequestBody UpdateAdminDto oldAdmin) {
        return new ResponseEntity<>(
                new RestResponse<>(service.updateAdmin(oldAdmin),
                        "Update admin successfully.",
                        "201"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<RestResponse<DeleteAdminResponseDto>> deleteUser(@PathVariable String userId) {
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteAdmin(userId),
                        "Delete admin successfully.",
                        "201"),
                HttpStatus.OK);
    }
}

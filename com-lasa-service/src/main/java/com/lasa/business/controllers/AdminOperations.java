package com.lasa.business.controllers;

import com.lasa.data.model.request.AdminRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface AdminOperations {
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Integer id);

    @PostMapping
    ResponseEntity<?> createAdmin(AdminRequestModel adminRequestModel);

    @PatchMapping
    ResponseEntity<?> updateAdmin(@RequestBody AdminRequestModel admin);

    @DeleteMapping
    ResponseEntity<?> deleteAdmin(@RequestParam List<Integer> id);
}

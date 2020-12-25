package com.bahar.crm.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

public interface ControllerInterface<DTO> {

    @PostMapping("/create")
    public ResponseEntity<DTO> create(@RequestBody DTO dto) throws URISyntaxException;

    @PutMapping("/update")
    public ResponseEntity<DTO> update(@RequestBody DTO dto) throws URISyntaxException;

    @GetMapping("/get/all")
    public ResponseEntity<Page<DTO>> getAll(Pageable pageable);

    @GetMapping("/get/{id}")
    public ResponseEntity<DTO> get(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFactor(@PathVariable Long id);
}

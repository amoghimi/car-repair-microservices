package com.carrepair.carrepairservice.controller;

import com.carrepair.carrepairservice.dto.RepairRequest;
import com.carrepair.carrepairservice.dto.RepairResponse;
import com.carrepair.carrepairservice.service.RepairService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CarRepairController {

    private final RepairService repairService;

    public CarRepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from car-repair-service";
    }

    @PostMapping("/repairs")
    public ResponseEntity<RepairResponse> createRepair(@Valid @RequestBody RepairRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairService.createRepair(request));
    }

    @GetMapping("/repairs/{id}")
    public ResponseEntity<RepairResponse> getRepair(@PathVariable Long id) {
        RepairResponse response = repairService.findById(id);
        return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }
}

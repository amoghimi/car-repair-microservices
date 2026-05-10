package com.carrepair.appointmentservice.controller;

import com.carrepair.appointmentservice.dto.AppointmentRequest;
import com.carrepair.appointmentservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/repair")
    public String createRepair(@Valid @RequestBody AppointmentRequest request) {
        return appointmentService.createRepairAppointment(request);
    }

    @PostMapping("/purchase")
    public String createPurchase(@Valid @RequestBody AppointmentRequest request) {
        return appointmentService.createPurchaseAppointment(request);
    }
}

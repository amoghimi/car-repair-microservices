package com.carrepair.appointmentservice.service;

import com.carrepair.appointmentservice.dto.AppointmentRequest;
import com.carrepair.appointmentservice.model.Appointment;
import com.carrepair.appointmentservice.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public String createRepairAppointment(AppointmentRequest request) {
        return create(request, "REPAIR");
    }

    public String createPurchaseAppointment(AppointmentRequest request) {
        return create(request, "PURCHASE");
    }

    private String create(AppointmentRequest request, String type) {
        Appointment appointment = new Appointment();
        appointment.setCustomerName(request.customerName());
        appointment.setAppointmentType(type);
        appointment.setPreferredDate(request.preferredDate());
        appointmentRepository.save(appointment);
        return type + " appointment created for " + request.customerName() + " on " + request.preferredDate();
    }
}

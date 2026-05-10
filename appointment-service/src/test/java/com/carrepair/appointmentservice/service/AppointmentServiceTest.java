package com.carrepair.appointmentservice.service;

import com.carrepair.appointmentservice.dto.AppointmentRequest;
import com.carrepair.appointmentservice.model.Appointment;
import com.carrepair.appointmentservice.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void createRepairAppointmentShouldPersistWithRepairType() {
        AppointmentRequest request = new AppointmentRequest("Max", "IGNORED", "2026-05-20");

        String result = appointmentService.createRepairAppointment(request);

        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository).save(captor.capture());
        assertThat(captor.getValue().getAppointmentType()).isEqualTo("REPAIR");
        assertThat(result).contains("REPAIR appointment created");
    }

    @Test
    void createPurchaseAppointmentShouldPersistWithPurchaseType() {
        AppointmentRequest request = new AppointmentRequest("Nina", "IGNORED", "2026-05-21");

        String result = appointmentService.createPurchaseAppointment(request);

        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository).save(captor.capture());
        assertThat(captor.getValue().getAppointmentType()).isEqualTo("PURCHASE");
        assertThat(result).contains("PURCHASE appointment created");
    }
}

package com.carrepair.appointmentservice.dto;

import jakarta.validation.constraints.NotBlank;

public record AppointmentRequest(@NotBlank String customerName, @NotBlank String appointmentType, @NotBlank String preferredDate) {
}

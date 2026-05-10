package com.carrepair.carrepairservice.dto;

import jakarta.validation.constraints.NotBlank;

public record RepairRequest(@NotBlank String customerName, @NotBlank String carModel, @NotBlank String issueDescription) {
}

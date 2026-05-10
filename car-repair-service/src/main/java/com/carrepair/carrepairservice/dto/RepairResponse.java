package com.carrepair.carrepairservice.dto;

public record RepairResponse(Long id, String customerName, String carModel, String issueDescription, String status) {
}

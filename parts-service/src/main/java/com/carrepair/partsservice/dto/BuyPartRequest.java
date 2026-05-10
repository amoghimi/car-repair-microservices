package com.carrepair.partsservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BuyPartRequest(@NotBlank String partName, @Min(1) int quantity) {
}

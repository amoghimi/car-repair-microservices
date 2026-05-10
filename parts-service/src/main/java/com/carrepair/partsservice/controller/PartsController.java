package com.carrepair.partsservice.controller;

import com.carrepair.partsservice.dto.BuyPartRequest;
import com.carrepair.partsservice.service.PartsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartsController {

    private final PartsService partsService;

    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping
    public List<String> listParts() {
        return partsService.listParts();
    }

    @PostMapping("/buy")
    public String buyPart(@Valid @RequestBody BuyPartRequest request) {
        return partsService.buyPart(request);
    }
}

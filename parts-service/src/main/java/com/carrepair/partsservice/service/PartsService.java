package com.carrepair.partsservice.service;

import com.carrepair.partsservice.dto.BuyPartRequest;
import com.carrepair.partsservice.model.Part;
import com.carrepair.partsservice.repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartsService {

    private final PartRepository partRepository;

    public PartsService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<String> listParts() {
        if (partRepository.count() == 0) {
            seed();
        }
        return partRepository.findAll().stream().map(p -> p.getName() + " (stock=" + p.getStock() + ")").toList();
    }

    public String buyPart(BuyPartRequest request) {
        Part part = partRepository.findByName(request.partName()).orElseThrow();
        if (part.getStock() < request.quantity()) {
            return "Not enough stock";
        }
        part.setStock(part.getStock() - request.quantity());
        partRepository.save(part);
        return "Purchased " + request.quantity() + " x " + request.partName();
    }

    private void seed() {
        List.of("Brake Pads", "Oil Filter", "Spark Plug", "Air Filter").forEach(n -> {
            Part p = new Part(); p.setName(n); p.setStock(50); partRepository.save(p);
        });
    }
}

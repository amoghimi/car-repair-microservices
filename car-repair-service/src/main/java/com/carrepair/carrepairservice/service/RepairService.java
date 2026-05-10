package com.carrepair.carrepairservice.service;

import com.carrepair.carrepairservice.dto.RepairRequest;
import com.carrepair.carrepairservice.dto.RepairResponse;
import com.carrepair.carrepairservice.model.Repair;
import com.carrepair.carrepairservice.repository.RepairRepository;
import org.springframework.stereotype.Service;

@Service
public class RepairService {
    private final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) { this.repairRepository = repairRepository; }

    public RepairResponse createRepair(RepairRequest request) {
        Repair repair = new Repair();
        repair.setCustomerName(request.customerName());
        repair.setCarModel(request.carModel());
        repair.setIssueDescription(request.issueDescription());
        repair.setStatus("CREATED");
        Repair saved = repairRepository.save(repair);
        return new RepairResponse(saved.getId(), saved.getCustomerName(), saved.getCarModel(), saved.getIssueDescription(), saved.getStatus());
    }

    public RepairResponse findById(Long id) {
        return repairRepository.findById(id)
                .map(r -> new RepairResponse(r.getId(), r.getCustomerName(), r.getCarModel(), r.getIssueDescription(), r.getStatus()))
                .orElse(null);
    }
}

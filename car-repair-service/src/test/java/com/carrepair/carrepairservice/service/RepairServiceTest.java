package com.carrepair.carrepairservice.service;

import com.carrepair.carrepairservice.dto.RepairRequest;
import com.carrepair.carrepairservice.dto.RepairResponse;
import com.carrepair.carrepairservice.model.Repair;
import com.carrepair.carrepairservice.repository.RepairRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepairServiceTest {

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairService repairService;

    @Test
    void createRepairShouldPersistAndReturnResponse() {
        RepairRequest request = new RepairRequest("Alice", "BMW", "Brake issue");
        Repair saved = new Repair();
        saved.setCustomerName("Alice");
        saved.setCarModel("BMW");
        saved.setIssueDescription("Brake issue");
        saved.setStatus("CREATED");

        when(repairRepository.save(org.mockito.ArgumentMatchers.any(Repair.class))).thenReturn(saved);

        RepairResponse response = repairService.createRepair(request);

        ArgumentCaptor<Repair> captor = ArgumentCaptor.forClass(Repair.class);
        verify(repairRepository).save(captor.capture());
        assertThat(captor.getValue().getStatus()).isEqualTo("CREATED");
        assertThat(response.customerName()).isEqualTo("Alice");
    }

    @Test
    void findByIdShouldReturnMappedResponseOrNull() {
        Repair repair = new Repair();
        repair.setCustomerName("Bob");
        repair.setCarModel("Audi");
        repair.setIssueDescription("Engine");
        repair.setStatus("DONE");
        when(repairRepository.findById(7L)).thenReturn(Optional.of(repair));
        when(repairRepository.findById(99L)).thenReturn(Optional.empty());

        RepairResponse found = repairService.findById(7L);
        RepairResponse missing = repairService.findById(99L);

        assertThat(found).isNotNull();
        assertThat(found.status()).isEqualTo("DONE");
        assertThat(missing).isNull();
    }
}

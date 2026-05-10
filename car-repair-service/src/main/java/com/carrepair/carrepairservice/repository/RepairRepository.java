package com.carrepair.carrepairservice.repository;

import com.carrepair.carrepairservice.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}

package com.hasancolk.vehiclemanagement.repository;

import com.hasancolk.vehiclemanagement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
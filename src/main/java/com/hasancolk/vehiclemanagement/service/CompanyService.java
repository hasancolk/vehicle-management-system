package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.request.CreateCompanyRequest;
import com.hasancolk.vehiclemanagement.request.UpdateCompanyRequest;
import com.hasancolk.vehiclemanagement.entity.Company;

public interface CompanyService {
    Company createCompany(Long userId, CreateCompanyRequest createCompanyRequest);
    Company updateCompany(Long companyId, UpdateCompanyRequest updateCompanyRequest);
    Company fetchCompanyById(Long companyId);
    void deleteCompany(Long companyId);
}

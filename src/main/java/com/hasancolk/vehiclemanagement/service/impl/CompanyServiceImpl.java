package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.enums.Role;
import com.hasancolk.vehiclemanagement.request.CreateCompanyRequest;
import com.hasancolk.vehiclemanagement.request.UpdateCompanyRequest;
import com.hasancolk.vehiclemanagement.entity.Company;
import com.hasancolk.vehiclemanagement.entity.User;
import com.hasancolk.vehiclemanagement.repository.CompanyRepository;
import com.hasancolk.vehiclemanagement.repository.UserRepository;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.service.CheckService;
import com.hasancolk.vehiclemanagement.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckService checkService;


    @Transactional
    @Override
    public Company createCompany(Long userId, CreateCompanyRequest createCompanyRequest) {

        checkService.checkUserIsNotInCompany(userId);

        Company company = new Company();
        company.setName(createCompanyRequest.getCompanyName());
        company.setIndustry(createCompanyRequest.getIndustry());
        company = companyRepository.save(company);

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new EntityNotFoundException("The user was not found!");
        }

        user.get().setCompanyId(company.getId());
        user.get().setRole(Role.ADMIN);
        userRepository.save(user.get());

        return company;
    }

    @Override
    public Company updateCompany(Long companyId, UpdateCompanyRequest updateCompanyRequest) {

        String companyName = updateCompanyRequest.getCompanyName();
        String industry = updateCompanyRequest.getIndustry();

        Optional<Company> company = companyRepository.findById(companyId);

        if(company.isEmpty()){
            throw new EntityNotFoundException("The company was not found!");
        }

        if(StringUtils.hasText(companyName)){
            company.get().setName(companyName);
        }

        if(StringUtils.hasText(industry)){
            company.get().setIndustry(industry);
        }

        return companyRepository.save(company.get());
    }

    @Override
    public Company fetchCompanyById(Long companyId) {

        Optional<Company> company = companyRepository.findById(companyId);

        if(company.isEmpty()){
            throw new EntityNotFoundException("The company was not found!");
        }

        return company.get();
    }

    @Override
    public void deleteCompany(Long companyId) {

        if(companyRepository.existsById(companyId)){
            throw new EntityNotFoundException("The company was not found!");
        }
        companyRepository.deleteById(companyId);
    }

}

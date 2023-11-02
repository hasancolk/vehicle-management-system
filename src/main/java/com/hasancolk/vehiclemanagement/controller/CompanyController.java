package com.hasancolk.vehiclemanagement.controller;

import com.hasancolk.vehiclemanagement.request.CreateCompanyRequest;
import com.hasancolk.vehiclemanagement.request.UpdateCompanyRequest;
import com.hasancolk.vehiclemanagement.entity.Company;
import com.hasancolk.vehiclemanagement.service.AuthenticationService;
import com.hasancolk.vehiclemanagement.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/createCompany")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody CreateCompanyRequest createCompanyRequest, HttpServletRequest request){
        Long userId = authenticationService.getUserId(request);
        return new ResponseEntity<>(companyService.createCompany(userId,createCompanyRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserId(@authenticationServiceImpl.getUserId(#request))")
    @PutMapping("/updateCompany")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody UpdateCompanyRequest updateCompanyRequest, HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(companyService.updateCompany(companyId,updateCompanyRequest), HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkCompanyAdminByUserId(@authenticationServiceImpl.getUserId(#request))")
    @DeleteMapping("/deleteCompany")
    public ResponseEntity<Void> deleteCompany(HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@checkServiceImpl.checkUserExistsInCompany(@authenticationServiceImpl.getUserId(#request),@authenticationServiceImpl.getCompanyIdByUserId(@authenticationServiceImpl.getUserId(#request)))")
    @GetMapping("/fetchCompany")
    public ResponseEntity<Company> fetchCompany(HttpServletRequest request){
        Long companyId = authenticationService.getCompanyIdByUserId(authenticationService.getUserId(request));
        return new ResponseEntity<>(companyService.fetchCompanyById(companyId), HttpStatus.OK);
    }

}

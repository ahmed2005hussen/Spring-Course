package com.ahmed.jobportal.company.controller;


import com.ahmed.jobportal.dto.CompanyDto;
import com.ahmed.jobportal.entity.Company;
import com.ahmed.jobportal.service.CompanyService;
import com.ahmed.jobportal.service.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor

//@CrossOrigin(origins = {"http://localhost:5173"})

public class CompanyController {


    private final CompanyService companyService;

    @GetMapping(version = "1.0")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyList);
    }

}

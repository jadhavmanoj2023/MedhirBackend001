package com.example.medhirBackend001.service;

import com.example.medhirBackend001.model.Company;
import com.example.medhirBackend001.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.medhirBackend001.exception.DuplicateResourceException;


import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    public Company createCompany(Company company) {
        if (companyRepository.findByEmail(company.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists!");
        }

        if (companyRepository.findByPhone(company.getPhone()).isPresent()) {
            throw new DuplicateResourceException("Phone number already exists!");
        }

        return companyRepository.save(company);
    }

}

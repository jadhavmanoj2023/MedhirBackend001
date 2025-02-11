package com.example.medhirBackend001.repository;

import com.example.medhirBackend001.model.Company;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company,String> {
    Optional<Company> findByEmail(String email);
    Optional<Company> findByPhone(String phone);
}

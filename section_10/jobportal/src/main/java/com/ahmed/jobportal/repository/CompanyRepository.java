package com.ahmed.jobportal.repository;

import com.ahmed.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository // we can don't put this annotation because
// we extends the JPARepository so spring will know by itself that this class is a Repository
public interface CompanyRepository extends JpaRepository<Company , Long> {
}

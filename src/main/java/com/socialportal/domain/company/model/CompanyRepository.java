package com.socialportal.domain.company.model;


import com.socialportal.domain.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {


}

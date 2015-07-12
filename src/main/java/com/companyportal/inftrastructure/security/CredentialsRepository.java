package com.companyportal.inftrastructure.security;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface CredentialsRepository extends MongoRepository<Credentials, String> {

}

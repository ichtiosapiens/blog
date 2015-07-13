package com.socialportal.application.security.domain;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface CredentialsRepository extends MongoRepository<Credentials, String> {

    public Credentials findByUsernameAndPassword(String username, String password);

}

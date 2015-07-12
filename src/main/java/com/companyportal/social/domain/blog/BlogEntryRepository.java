package com.companyportal.social.domain.blog;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogEntryRepository extends MongoRepository<BlogEntry, String> {

}

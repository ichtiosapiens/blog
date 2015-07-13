package com.socialportal.domain.blog.repositories;


import com.socialportal.domain.blog.model.BlogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogEntryRepository extends MongoRepository<BlogEntry, String> {


}

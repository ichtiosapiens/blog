package com.socialportal.domain.blog.services;

import com.socialportal.domain.blog.model.BlogEntry;
import com.socialportal.domain.blog.model.BlogEntryComment;
import com.socialportal.domain.blog.repositories.BlogEntryRepository;
import com.socialportal.domain.blog.repositories.BlogEntryCrudRepository;
import com.socialportal.domain.blog.repositories.BlogEntrySearchDto;
import com.socialportal.domain.blog.repositories.BlogEntrySearchDtoFactory;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogEntryService {

    public static final int pageCount = 20;
    @Autowired
    private BlogEntryRepository blogEntryRepository;

    @Autowired
    private BlogEntryCrudRepository blogEntryCrudRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BlogEntrySearchDtoFactory blogEntrySearchDtoFactory;

    public void save(BlogEntry blogEntry) {
        blogEntryRepository.save(blogEntry);
        blogEntryCrudRepository.save(blogEntrySearchDtoFactory.createBlogEntrySearchDto(blogEntry));

    }

    public Page<BlogEntry> getAllEntries(Integer pageNumber) {
        int rangeMin = pageNumber * pageCount;
        return  blogEntryRepository.findAll(new PageRequest(rangeMin, rangeMin+pageCount));
    }

    public BlogEntry getBlogEntry(String postId) {
        return blogEntryRepository.findOne(postId);
    }

    public void addComment(String blogEntryId, String commentContent) {
        BlogEntryComment blogEntryComment = new BlogEntryComment();
        blogEntryComment.setCommentContent(commentContent);

        Update update =  new Update();
        mongoOperations.updateFirst(
                 new Query(Criteria.where("entryId").is(blogEntryId)),
                 update.addToSet("comments").value(blogEntryComment), BlogEntry.class);
    }

    public Page<BlogEntry> guery(String queryPhrase, Integer pageNumber) {
        List<BlogEntrySearchDto> entries;
        if(!StringUtils.isEmpty(queryPhrase)){
            entries = blogEntryCrudRepository.findByTitleContainsOrTextContains(queryPhrase, queryPhrase);
        }else{
            entries = Lists.newArrayList(blogEntryCrudRepository.findAll());
        }

        List<String> entryIds = entries.stream().map(BlogEntrySearchDto::getEntryId).collect(Collectors.toList());
        return new PageImpl<BlogEntry>(Lists.newArrayList(blogEntryRepository.findAll(entryIds)));

    }

    public Page<BlogEntry> guery2(String queryPhrase, Integer pageNumber) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage();
        PageRequest pageRequest = new PageRequest(pageNumber,20);
        List<BlogEntry> entries;
        if(!StringUtils.isEmpty(queryPhrase)){
            criteria = criteria.matchingAny(queryPhrase.split(" "));
            Query query = TextQuery.queryText(criteria)
                    .with(pageRequest);
            entries = mongoOperations.find(query, BlogEntry.class);
        }else{
            entries = mongoOperations.findAll(BlogEntry.class);
        }
        return new PageImpl<BlogEntry>(entries );

    }
}

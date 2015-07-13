package com.socialportal.domain.blog.services;

import com.socialportal.domain.blog.model.BlogEntry;
import com.socialportal.domain.blog.model.BlogEntryComment;
import com.socialportal.domain.blog.repositories.BlogEntryRepository;
import com.socialportal.domain.blog.infrastructure.seach.BlogEntrySearchRepository;
import com.socialportal.domain.blog.infrastructure.seach.BlogEntrySearchDto;
import com.socialportal.domain.blog.infrastructure.seach.BlogEntrySearchDtoFactory;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Blog related service.
 * Responsible for querying, adding and commenting blog entries.
 */
@Service
public class BlogEntryService {

    public static final int pageCount = 20;
    @Autowired
    private BlogEntryRepository blogEntryRepository;

    @Autowired
    private BlogEntrySearchRepository blogEntrySearchRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BlogEntrySearchDtoFactory blogEntrySearchDtoFactory;

    public BlogEntry save(BlogEntry blogEntry) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        blogEntry.setUserDisplayId(auth.getName());

        blogEntry = blogEntryRepository.save(blogEntry);
        blogEntrySearchRepository.save(blogEntrySearchDtoFactory.createBlogEntrySearchDto(blogEntry));
        return blogEntry;
    }

    public Page<BlogEntry> getAllEntries(Integer pageNumber) {
        int rangeMin = pageNumber * pageCount;
        return  blogEntryRepository.findAll(new PageRequest(rangeMin, rangeMin+pageCount));
    }

    public BlogEntry getBlogEntry(String postId) {
        return blogEntryRepository.findOne(postId);
    }

    public BlogEntryComment addComment(String blogEntryId, String commentContent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        BlogEntryComment blogEntryComment = new BlogEntryComment();
        blogEntryComment.setCommentContent(commentContent);
        blogEntryComment.setCommentingUserId(auth.getName());

        Update update =  new Update();
        mongoOperations.updateFirst(
                 new Query(Criteria.where("entryId").is(blogEntryId)),
                 update.addToSet("comments").value(blogEntryComment), BlogEntry.class);
        return blogEntryComment;
    }

    public Page<BlogEntry> guery(String queryPhrase, Integer pageNumber) {
        List<BlogEntrySearchDto> entries;
        if(!StringUtils.isEmpty(queryPhrase)){
            entries = blogEntrySearchRepository.findByTitleContainsOrTextContains(queryPhrase, queryPhrase);
        }else{
            entries = Lists.newArrayList(blogEntrySearchRepository.findAll());
        }

        List<String> entryIds = entries.stream().map(BlogEntrySearchDto::getEntryId).collect(Collectors.toList());
        return new PageImpl(Lists.newArrayList(blogEntryRepository.findAll(entryIds)));

    }
}

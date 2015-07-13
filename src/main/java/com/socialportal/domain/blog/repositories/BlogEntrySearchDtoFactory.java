package com.socialportal.domain.blog.repositories;

import com.socialportal.domain.blog.model.BlogEntry;
import org.springframework.stereotype.Component;

@Component
public class BlogEntrySearchDtoFactory {

    public BlogEntrySearchDto createBlogEntrySearchDto(BlogEntry blogEntry) {
        BlogEntrySearchDto dto = new BlogEntrySearchDto();
        dto.setEntryId(blogEntry.getEntryId());
        dto.setTitle(blogEntry.getTitle());
        dto.setText(blogEntry.getText());
        return dto;
    }


}

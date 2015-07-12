package com.companyportal.social.domain.blog.search;

import com.companyportal.social.domain.blog.BlogEntry;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

public class BlogEntrySearchDto {

    @Id
    private String entryId;
    @Field
    private String title;
    @Field
    private String text;



    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

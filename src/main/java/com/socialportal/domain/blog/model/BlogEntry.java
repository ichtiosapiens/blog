package com.socialportal.domain.blog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "blogEntries")
public class BlogEntry {

    @Id
    private String entryId;

    private String title;

    private String text;

    private List<BlogEntryComment> comments;

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

    public List<BlogEntryComment> getComments() {
        return comments;
    }

    public void setComments(List<BlogEntryComment> comments) {
        this.comments = comments;
    }
}

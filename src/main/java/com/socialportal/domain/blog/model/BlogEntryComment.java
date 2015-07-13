package com.socialportal.domain.blog.model;


public class BlogEntryComment {

    private String commentingUserId;
    private String userDisplayName;
    private String commentContent;

    public String getCommentingUserId() {
        return commentingUserId;
    }

    public void setCommentingUserId(String commentingUserId) {
        this.commentingUserId = commentingUserId;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}

package com.socialportal.domain.blog.infrastructure.controllers;


import com.socialportal.domain.blog.model.BlogEntry;
import com.socialportal.domain.blog.services.BlogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogController {

    public static final String BLOG_PAGE = "blogPage";
    public static final String BLOG_ENTRY = "blogEntry";
    public static final String QUERY_PHRASE = "queryPhrase";

    @Autowired
    private BlogEntryService blogEntryService;

    @RequestMapping("/")
    public String enterBlog(Model model, HttpServletRequest request) {
        String queryPhrase =  request.getParameter(QUERY_PHRASE);
        Page<BlogEntry> page = blogEntryService.guery(queryPhrase, 0);
        model.addAttribute(BLOG_PAGE, page);
        model.addAttribute(QUERY_PHRASE, queryPhrase);
        return "home";
    }

    @RequestMapping("/viewPost/{postId}")
    public String viewBlogEntry(Model model, @PathVariable("postId") String postId) {
        BlogEntry blobEntry = blogEntryService.getBlogEntry(postId);
        model.addAttribute(BLOG_ENTRY, blobEntry);
        return "viewPost";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(String blogEntryId, String commentContent) {
        blogEntryService.addComment(blogEntryId, commentContent);
        return "redirect:/viewPost/"+blogEntryId;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(final BlogEntry blogEntry) {
        blogEntryService.save(blogEntry);
        return "redirect:/";
    }


}

package com.companyportal.social.domain.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogController {

    public static final String QUERY_PHRASE = "queryPhrase";

    @Autowired
    private BlogEntryService blogEntryService;

    @RequestMapping(value = "/listEntries", method = RequestMethod.GET)
    public @ResponseBody
    Page<BlogEntry> getAllEntries(HttpServletRequest request) {
        String queryPhrase = (String)request.getAttribute(QUERY_PHRASE);
        return blogEntryService.guery(queryPhrase, 0);
    }


    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(final BlogEntry blogEntry) {
        blogEntryService.save(blogEntry);
        return "redirect:/";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(String blogEntryId, String commentContent) {
        blogEntryService.addComment(blogEntryId, commentContent);
        return "redirect:/viewPost/"+blogEntryId;
    }

}

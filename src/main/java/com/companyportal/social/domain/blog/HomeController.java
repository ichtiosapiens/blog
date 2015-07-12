package com.companyportal.social.domain.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    public static final String BLOG_PAGE = "blogPage";
    public static final String BLOG_ENTRY = "blogEntry";
    public static final String QUERY_PHRASE = "queryPhrase";
    @Autowired
    private BlogEntryService blogEntryService;

    @RequestMapping("/")
    public String enterBlog(Model model, HttpServletRequest request){
        String queryPhrase = (String)request.getParameter(QUERY_PHRASE);
        Page<BlogEntry> page = blogEntryService.guery(queryPhrase, 0);
        model.addAttribute(BLOG_PAGE, page);
        return "home";
    }

    @RequestMapping("/viewPost/{postId}")
    public String viewBlogEntry(Model model, @PathVariable("postId")String postId){
        BlogEntry blobEntry = blogEntryService.getBlogEntry(postId);
        model.addAttribute(BLOG_ENTRY, blobEntry);
        return "viewPost";
    }




}

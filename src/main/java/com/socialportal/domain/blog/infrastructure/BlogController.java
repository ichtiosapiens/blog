package com.socialportal.domain.blog.infrastructure;

import com.google.common.collect.Sets;
import com.socialportal.application.security.domain.Credentials;
import com.socialportal.application.security.domain.CredentialsRepository;
import com.socialportal.domain.blog.model.BlogEntry;
import com.socialportal.domain.blog.services.BlogEntryService;
import com.socialportal.domain.company.model.Company;
import com.socialportal.domain.company.model.CompanyRepository;
import com.socialportal.domain.company.model.Department;
import com.socialportal.domain.user.model.User;
import com.socialportal.domain.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

//    @PreAuthorize("hasRole('USER')")
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

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;


    //TODO: to be removed
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public @ResponseBody
    String loadData(HttpServletRequest request) {
        Credentials credentials1, credentials2;
        credentials1 = new Credentials();
        credentials1.setPassword("user");
        credentials1.setUsername("user");
        credentials1 = credentialsRepository.save(credentials1);

        credentials2 = new Credentials();
        credentials2.setPassword("admin");
        credentials2.setUsername("admin");
        credentials2 = credentialsRepository.save(credentials2);

        User  user = new User();
        user.setDisplayName("User");
        user.setFirstName("user first name");
        user.setUsername("user");
        user.setLastName("user last name");
        user.setRoles(Sets.newHashSet(new SimpleGrantedAuthority("USER")));

        user = userRepository.save(user);

        User  admin = new User();
        admin.setDisplayName("Admin");
        admin.setFirstName("admin first name");
        admin.setUsername("admin");
        admin.setLastName("admin last name");
        admin.setRoles(Sets.newHashSet(new SimpleGrantedAuthority("ADMIN")));

        admin = userRepository.save(admin);

        Company company = new Company();
        company.setCompanyName("Crossover");

        Department department = new Department("IT");
        department.setEmployees(Sets.<String>newHashSet(user.getUsername(), admin.getUsername()));
        company.setDepartments(Sets.newHashSet(new Department("IT")));

        company = companyRepository.save(company);

        user.setCompanyId(company.getCompanyId());
        userRepository.save(user);
        admin.setCompanyId(company.getCompanyId());
        userRepository.save(admin);

        return "pykło kurde";
    }


}

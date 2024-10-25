package com.example.elastic_search.api;
import com.example.elastic_search.model.User;
import com.example.elastic_search.repository.UserRepository;
import com.example.elastic_search.util.RepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class UserViewController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<User> users = RepositoryUtil.convertIterableToList(userRepository.findAll()); // Fetch data from service
        model.addAttribute("users", users); // Pass data to the view
        return "home"; // Refers to 'home.html' in 'templates' folder
    }

}

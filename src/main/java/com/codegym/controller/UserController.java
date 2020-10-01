package com.codegym.controller;

import java.security.Principal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/*")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user(Principal principal) {
        // Get authenticated user name from Principal
        System.out.println(principal.getName());
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        return "admin";
    }

    @GetMapping("/news")
    public String news(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            System.out.println(user.getUsername());
            user.getAuthorities().forEach(System.out::println);
            System.out.println("user.isAccountNonExpired() = " + user.isAccountNonExpired());
            System.out.println("user.getUsername() = " + user.getUsername());
            System.out.println("user.getPassword() = " + user.getPassword());
            System.out.println("user.isAccountNonLocked() = " + user.isAccountNonLocked());
            System.out.println("user.isCredentialsNonExpired() = " + user.isCredentialsNonExpired());
        } else {
            String username = principal.toString();
        }
        return "news";
    }
}

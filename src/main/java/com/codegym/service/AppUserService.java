package com.codegym.service;

import com.codegym.model.AppRole;
import com.codegym.model.AppUser;
import com.codegym.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public Iterable<AppUser> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        List<AppRole> roles = new ArrayList<>();
        roles.add(appUser.getAppRole());
        User user = new User(appUser.getUsername(),appUser.getPassword(),roles);
        return user;
    }
}

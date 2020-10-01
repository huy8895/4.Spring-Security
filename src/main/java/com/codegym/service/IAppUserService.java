package com.codegym.service;

import com.codegym.model.AppUser;

public interface IAppUserService {
    Iterable<AppUser> getUserList();

    AppUser getUser(Long id);

    AppUser saveUser(AppUser user);
}

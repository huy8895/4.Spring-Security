package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}

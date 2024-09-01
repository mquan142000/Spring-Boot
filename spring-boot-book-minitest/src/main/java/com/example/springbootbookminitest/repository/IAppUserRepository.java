package com.example.springbootbookminitest.repository;

import com.example.springbootbookminitest.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IAppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByName(String name);

}

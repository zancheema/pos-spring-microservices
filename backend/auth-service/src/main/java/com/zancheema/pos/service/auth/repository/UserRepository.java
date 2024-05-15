package com.zancheema.pos.service.auth.repository;

import com.zancheema.pos.service.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByEmail(String email);
}

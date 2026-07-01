package com.faraldma.tictactoe.datasource.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.faraldma.tictactoe.datasource.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findUserByLogin(String login);
}

package ru.java.votingsystem.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}
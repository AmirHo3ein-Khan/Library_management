package ir.librarymanagement.repository;

import ir.librarymanagement.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> loginUser(String username , String password);
}

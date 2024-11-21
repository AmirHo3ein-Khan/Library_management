package ir.librarymanagement.service;

import ir.librarymanagement.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> loginUser(String username , String password);
}

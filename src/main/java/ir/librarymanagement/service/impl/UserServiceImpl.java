package ir.librarymanagement.service.impl;

import ir.librarymanagement.exception.NotFoundException;
import ir.librarymanagement.model.User;
import ir.librarymanagement.repository.UserRepository;
import ir.librarymanagement.service.UserService;
import ir.librarymanagement.util.Printer;

import javax.persistence.NoResultException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = Optional.empty();
        try {
            user = userRepository.loginUser(username, password);
        } catch (NoResultException e){
            Printer.printError("User not found.");
        }
        return user;
    }
}

package ir.librarymanagement.util;

import ir.librarymanagement.repository.*;
import ir.librarymanagement.repository.impl.*;
import ir.librarymanagement.service.*;
import ir.librarymanagement.service.impl.*;
import lombok.Getter;

public class ApplicationContext {
    public static String[] starterMenu = {"LOGIN", "SIGN IN" , "EXIT"};
    public static String[] librarianMenu = {"ADD BOOK", "REMOVE BOOK", "UPDATE BOOK", "SEE ALL BOOKS","SEE ALL CATEGORY", "EXIT"};
    public static String[] memberMenu = {"SEE ALL CATEGORIES", "SEE ALL BOOKS", "BORROW A BOOK ", "RETURN A BOOK","SEE ALL BORROWED BOOKS","EXIT"};

    @Getter
    private static BookRepository bookRepository;
    @Getter
    private static CategoryRepository categoryRepository;
    private static LibrarianRepository librarianRepository;
    private static LibraryMemberRepository libraryMemberRepository;
    private static UserRepository userRepository;
    @Getter
    private static BookService bookService;
    @Getter
    private static CategoryService categoryService;
    @Getter
    private static LibrarianService librarianService;
    @Getter
    private static LibraryMemberService libraryMemberService;
    @Getter
    private static UserService userService;

    static {
        bookRepository = new BookRepositoryImpl();
        categoryRepository = new CategoryRepositoryImpl();
        librarianRepository = new LibrarianRepositoryImpl();
        libraryMemberRepository = new LibraryMemberRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        bookService = new BookServiceImpl(bookRepository);
        categoryService = new CategoryServiceImpl(categoryRepository);
        librarianService = new LibrarianServiceImpl(librarianRepository);
        libraryMemberService = new LibraryMemberServiceImpl(libraryMemberRepository);
        userService = new UserServiceImpl(userRepository);
    }

}

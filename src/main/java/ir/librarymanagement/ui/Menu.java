package ir.librarymanagement.ui;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.Category;
import ir.librarymanagement.model.LibraryMember;
import ir.librarymanagement.model.User;
import ir.librarymanagement.model.enums.BookStatus;
import ir.librarymanagement.model.enums.UserType;
import ir.librarymanagement.util.ApplicationContext;
import ir.librarymanagement.util.Printer;
import ir.librarymanagement.util.Utility;

import java.util.List;
import java.util.Optional;

public class Menu {
    public static void startProject() {
        Printer.printMenu(ApplicationContext.starterMenu, "WELCOME TO LIBRARY");
        int choice = Utility.getInt("Enter your choice : ");
        switch (choice) {
            case 1:
                Optional<User> user = longIn();
                if (user.isPresent()) {
                    if (user.get().getUserType().equals(UserType.LIBRARIAN)) {
                        librarianAccess();
                    } else if (user.get().getUserType().equals(UserType.MEMBER)) {
                        memberAccess(user.get());
                    }
                } else {
                    Printer.printError("Please Try Again!");
                    startProject();
                }
                break;
            case 2:
                signUp();
                Printer.printDescription("Signup successfully 😊 ");
                startProject();
                break;
            case 3:
                break;
        }
    }

    private static Optional<User> longIn() {
        String username = Utility.getString("Enter your username : ");
        String password = Utility.getString("Enter your password : ");
        return ApplicationContext.getUserService().loginUser(username, password);
    }

    private static void signUp() {
        String username = Utility.getString("Enter your username for signup : ");
        String password = Utility.getString("Enter your password for signup : ");
        LibraryMember libraryMember = LibraryMember.builder()
                .username(username)
                .password(password)
                .userType(UserType.MEMBER).build();
        ApplicationContext.getLibraryMemberService().create(libraryMember);
    }

    private static void librarianAccess() {
        Printer.printMenu(ApplicationContext.librarianMenu, "WELCOME");
        int choice = Utility.getInt("Enter your choice : ");
        switch (choice) {
            case 1:
                creatBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                updateBook();
                break;
            case 4:
                getAllBooks();
                break;
            case 5:
                getAllCategories();
                break;
            case 6:
                startProject();
                break;
        }
        librarianAccess();
    }

    private static void getAllCategories() {
        List<Category> categories = ApplicationContext.getCategoryService().findCategoriesWithOneOrMoreBooks();
        Printer.printList(categories);
    }


    private static void getAllBooks() {
        List<Book> all = ApplicationContext.getBookService().findAll();
        Printer.printList(all);
    }

    private static void creatBook() {
        String bookName = Utility.getString("Enter book name : ");
        String bookCategory = Utility.getString("Enter book category : ");
        Optional<Category> existCategory = ApplicationContext.getCategoryService().findACategoryByName(bookCategory);
        Category category = null;
        if (existCategory.isPresent()) {
            category = existCategory.get();
        } else {
            category = Category.builder().name(bookCategory).build();
            ApplicationContext.getCategoryService().create(category);
        }
        Book book = Book.builder()
                .subject(bookName)
                .category(category)
                .bookStatus(BookStatus.AVAILABLE).build();
        ApplicationContext.getBookService().create(book);
    }

    private static void updateBook() {
        getAllBooks();
        Long bookId = Utility.getLong("Enter id of book you want update : ");
        Optional<Book> book1 = ApplicationContext.getBookService().find(bookId);
        if (book1.get().getBookStatus().equals(BookStatus.AVAILABLE)) {
            String bookName = Utility.getString("Enter book name : ");
            String bookCategory = Utility.getString("Enter book category : ");
            Category categoryOfBook = ApplicationContext.getBookService().findCategoryOfBook(book1.get());
            Category category = Category.builder().name(bookCategory).id(categoryOfBook.getId()).build();
            Book book = Book.builder()
                    .id(bookId)
                    .subject(bookName)
                    .category(category)
                    .bookStatus(BookStatus.AVAILABLE).build();
            ApplicationContext.getCategoryService().update(category);
            ApplicationContext.getBookService().update(book);
        } else {
            Printer.printError("This book was borrowed please try when it's available ! ");
        }
    }

    private static void deleteBook() {
        getAllBooks();
        Long bookId = Utility.getLong("Enter id of book you want remove : ");
        Optional<Book> book = ApplicationContext.getBookService().find(bookId);
        if (book.get().getBookStatus().equals(BookStatus.AVAILABLE)) {
            ApplicationContext.getBookService().delete(bookId);
        } else Printer.printError("This book was borrowed please try when it's available ! ");
    }

    private static void memberAccess(User user) {
        Printer.printMenu(ApplicationContext.memberMenu, "WELCOME");
        int choice = Utility.getInt("Enter your choice : ");
        switch (choice) {
            case 1:
                List<Category> all = ApplicationContext.getCategoryService().findAll();
                Printer.printList(all);
                break;
            case 2:
                getAllBooks();
                break;
            case 3:
                borrowedABook(user);
                break;
            case 4:
                returnABook(user);
                break;
            case 5:
                printBorrowedBooks(user);
                break;
            case 6:
                startProject();
                break;
        }
        memberAccess(user);
    }

    private static void printBorrowedBooks(User user) {
        Optional<LibraryMember> libraryMember = ApplicationContext.getLibraryMemberService().find(user.getId());
        List<Book> borrowedBooks = libraryMember.get().getBorrowedBooks();
        if (!borrowedBooks.isEmpty()){
        Printer.printList(borrowedBooks);
        } else {
            Printer.printError("you don't have any borrowed book!");
        }
    }


    private static void borrowedABook(User user) {
        List<Book> books = ApplicationContext.getBookService().availableBooks();
        if (!books.isEmpty()) {
            Printer.printList(books);
            Long bookId = Utility.getLong("Enter id of book you want borrowed : ");
            Optional<Book> book = ApplicationContext.getBookService().find(bookId);
            Optional<LibraryMember> libraryMember = ApplicationContext.getLibraryMemberService().find(user.getId());
            if (book.isPresent() && libraryMember.isPresent()) {
                ApplicationContext.getLibraryMemberService().borrowBook(libraryMember.get(), book.get());
            } else {
                Printer.printError("Something went wrong. Please try again ! ");
            }
        } else {
            Printer.printError("Books are not available to borrow ! ");
        }
    }

    private static void returnABook(User user) {
        Optional<LibraryMember> libraryMember = ApplicationContext.getLibraryMemberService().find(user.getId());
        List<Book> borrowedBooks = libraryMember.get().getBorrowedBooks();
        if (!borrowedBooks.isEmpty()) {
            Printer.printList(borrowedBooks);
            Long bookId = Utility.getLong("Enter id of book you want return : ");
            Optional<Book> book = ApplicationContext.getBookService().find(bookId);
            if (book.isPresent()) {
                ApplicationContext.getLibraryMemberService().returnBook(libraryMember.get(), book.get());
            } else {
                Printer.printError("Something went wrong. Please try again ! ");
            }
        } else Printer.printError("you don't have any borrowed book!");
    }

}

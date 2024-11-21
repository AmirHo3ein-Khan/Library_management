package ir.librarymanagement.service.impl;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.LibraryMember;
import ir.librarymanagement.model.enums.BookStatus;
import ir.librarymanagement.repository.BookRepository;
import ir.librarymanagement.repository.LibraryMemberRepository;
import ir.librarymanagement.service.LibraryMemberService;
import ir.librarymanagement.service.base.BaseServiceImpl;
import ir.librarymanagement.util.ApplicationContext;

public class LibraryMemberServiceImpl extends BaseServiceImpl<LibraryMember,Long, LibraryMemberRepository> implements LibraryMemberService {

    private static final BookRepository bookRepository = ApplicationContext.getBookRepository();
    public LibraryMemberServiceImpl(LibraryMemberRepository repository) {
        super(repository);
    }

    @Override
    public void borrowBook(LibraryMember libraryMember , Book book) {
        libraryMember.getBorrowedBooks().add(book);
        book.setBookStatus(BookStatus.BORROWED);
        book.setBorrowedBookBy(libraryMember);
        bookRepository.update(book);
    }

    @Override
    public void returnBook(LibraryMember libraryMember, Book book) {
        libraryMember.getBorrowedBooks().remove(book);
        book.setBorrowedBookBy(null);
        book.setBookStatus(BookStatus.AVAILABLE);
        bookRepository.update(book);
    }
}

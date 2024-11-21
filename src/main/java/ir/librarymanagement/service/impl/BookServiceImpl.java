package ir.librarymanagement.service.impl;

import ir.librarymanagement.Application;
import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.Category;
import ir.librarymanagement.repository.BookRepository;
import ir.librarymanagement.service.BookService;
import ir.librarymanagement.service.base.BaseServiceImpl;
import ir.librarymanagement.util.ApplicationContext;

import java.util.List;

public class BookServiceImpl extends BaseServiceImpl<Book,Long, BookRepository> implements BookService {
    public BookServiceImpl(BookRepository repository) {
        super(repository);
    }

    @Override
    public Category findCategoryOfBook(Book book) {
        return ApplicationContext.getBookRepository().findCategoryOfBook(book);
    }

    @Override
    public List<Book> availableBooks() {
        return ApplicationContext.getBookRepository().availableBooks();
    }
}

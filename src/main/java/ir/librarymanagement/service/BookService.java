package ir.librarymanagement.service;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.Category;
import ir.librarymanagement.service.base.BaseService;

import java.util.List;

public interface BookService extends BaseService<Book , Long> {
    Category findCategoryOfBook (Book book);
    List<Book> availableBooks();
}

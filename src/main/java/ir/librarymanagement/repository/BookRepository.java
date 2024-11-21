package ir.librarymanagement.repository;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.Category;
import ir.librarymanagement.repository.base.BaseRepository;

import java.util.List;

public interface BookRepository extends BaseRepository <Book , Long> {
    Category findCategoryOfBook (Book book);
    List<Book> availableBooks();
}

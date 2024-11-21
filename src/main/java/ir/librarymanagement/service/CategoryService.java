package ir.librarymanagement.service;

import ir.librarymanagement.model.Category;
import ir.librarymanagement.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends BaseService<Category , Long> {
    Optional<Category> findACategoryByName(String name);
    List<Category> findCategoriesWithOneOrMoreBooks();
}

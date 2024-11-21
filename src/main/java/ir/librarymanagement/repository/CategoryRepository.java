package ir.librarymanagement.repository;

import ir.librarymanagement.model.Category;
import ir.librarymanagement.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findACategoryByName(String name);
    List<Category> allCategoriesWithOneOrMoreBooks();

}

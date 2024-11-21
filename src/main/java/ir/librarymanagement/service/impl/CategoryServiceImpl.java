package ir.librarymanagement.service.impl;

import ir.librarymanagement.model.Category;
import ir.librarymanagement.repository.CategoryRepository;
import ir.librarymanagement.service.CategoryService;
import ir.librarymanagement.service.base.BaseServiceImpl;
import ir.librarymanagement.util.ApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl extends BaseServiceImpl<Category,Long, CategoryRepository> implements CategoryService {
    private final CategoryRepository categoryRepository = ApplicationContext.getCategoryRepository();
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Category> findACategoryByName(String name) {
        Optional<Category> category = Optional.empty();
        try {
        category =  categoryRepository.findACategoryByName(name);
        } catch (NoResultException ignored){}
        return category;
    }

    @Override
    public List<Category> findCategoriesWithOneOrMoreBooks() {
        List<Category> categories = null;
        try {
        categories = categoryRepository.allCategoriesWithOneOrMoreBooks();
        } catch (NoResultException ignored){}
        return categories;
    }
}

package ir.librarymanagement.repository.impl;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.Category;
import ir.librarymanagement.repository.CategoryRepository;
import ir.librarymanagement.repository.base.BaseRepositoryImpl;
import ir.librarymanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, Long> implements CategoryRepository {
    private final EntityManager em = EntityManagerSingleton.getEntityManager();

    @Override
    protected Class<Category> getEntityType() {
        return Category.class;
    }


    @Override
    public Optional<Category> findACategoryByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        cq.select(root).where(cb.equal(root.get("name"), name));
        return Optional.ofNullable(em.createQuery(cq).getSingleResult());
    }

    @Override
    public List<Category> allCategoriesWithOneOrMoreBooks() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        Join<Category, Book> books = root.join("books");
        cq.select(root).distinct(true);
        return em.createQuery(cq).getResultList();
    }
}

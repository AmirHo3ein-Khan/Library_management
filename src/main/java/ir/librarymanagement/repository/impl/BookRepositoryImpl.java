package ir.librarymanagement.repository.impl;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.Category;
import ir.librarymanagement.model.enums.BookStatus;
import ir.librarymanagement.repository.BookRepository;
import ir.librarymanagement.repository.base.BaseRepositoryImpl;
import ir.librarymanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book , Long> implements BookRepository {
    private final EntityManager em = EntityManagerSingleton.getEntityManager();
    @Override
    protected Class<Book> getEntityType() {
        return Book.class;
    }
    @Override
    public Category findCategoryOfBook(Book book) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root.get("category")).where(cb.equal(root, book));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public List<Book> availableBooks() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        cq.select(root).where(cb.equal(root.get("bookStatus") , BookStatus.AVAILABLE));
        return em.createQuery(cq).getResultList();
    }
}

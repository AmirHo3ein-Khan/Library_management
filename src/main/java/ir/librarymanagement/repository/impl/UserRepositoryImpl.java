package ir.librarymanagement.repository.impl;

import ir.librarymanagement.exception.NotFoundException;
import ir.librarymanagement.model.User;
import ir.librarymanagement.repository.UserRepository;
import ir.librarymanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager em = EntityManagerSingleton.getEntityManager();
    @Override
    public Optional<User> loginUser(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate usernameCondition = cb.equal(root.get("username") , username);
        Predicate passwordCondition = cb.equal(root.get("password") , password);
        Predicate condition = cb.and(usernameCondition , passwordCondition);
        cq.select(root).where(condition);
        return Optional.ofNullable(em.createQuery(cq).getSingleResult());
    }
}

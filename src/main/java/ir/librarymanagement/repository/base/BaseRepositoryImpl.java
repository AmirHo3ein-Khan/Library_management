package ir.librarymanagement.repository.base;

import ir.librarymanagement.exception.NotFoundException;
import ir.librarymanagement.model.base.BaseModel;
import ir.librarymanagement.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseRepository<T, ID> {
    private final EntityManager em = EntityManagerSingleton.getEntityManager();

    @Override
    public void create(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try {
            em.getTransaction().begin();
            Optional<T> optional = this.find(entity.getId());
            if (optional.isPresent()) {
                em.merge(entity);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                throw new NotFoundException();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Optional<T> find(ID id) {
        T entity = null;
        try {
            entity = em.find(getEntityType(), id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Optional<T> foundedEntity = Optional.ofNullable(entity);
        if (foundedEntity.isPresent()) {
            return foundedEntity;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void delete(ID id) {
        Optional<T> t = find(id);
        try {
            if (t.isPresent()) {
                em.getTransaction().begin();
                T merge = em.merge(t.get());
                em.remove(merge);
                em.getTransaction().commit();
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityType());
        Root<T> root = cq.from(getEntityType());
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Long getCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(getEntityType());
        cq.select(cb.count(root));
        return em.createQuery(cq).getSingleResult();
    }

    protected abstract Class<T> getEntityType();
}

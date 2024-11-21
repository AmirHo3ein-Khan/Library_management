package ir.librarymanagement.service.base;

import ir.librarymanagement.model.base.BaseModel;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService <T extends BaseModel<ID>,ID extends Serializable>{
    void create (T entity);
    void update (T entity);
    Optional<T> find (ID id);
    void delete (ID id);
    List<T> findAll ();
    Long getCount();
}

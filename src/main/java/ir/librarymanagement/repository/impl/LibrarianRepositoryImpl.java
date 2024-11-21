package ir.librarymanagement.repository.impl;

import ir.librarymanagement.model.Librarian;
import ir.librarymanagement.repository.LibrarianRepository;
import ir.librarymanagement.repository.base.BaseRepositoryImpl;

public class LibrarianRepositoryImpl extends BaseRepositoryImpl<Librarian, Long> implements LibrarianRepository {
    @Override
    protected Class<Librarian> getEntityType() {
        return Librarian.class;
    }
}

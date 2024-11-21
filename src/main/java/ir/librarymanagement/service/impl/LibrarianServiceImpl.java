package ir.librarymanagement.service.impl;

import ir.librarymanagement.model.Librarian;
import ir.librarymanagement.repository.LibrarianRepository;
import ir.librarymanagement.service.LibrarianService;
import ir.librarymanagement.service.base.BaseServiceImpl;

public class LibrarianServiceImpl extends BaseServiceImpl<Librarian,Long, LibrarianRepository> implements LibrarianService {
    public LibrarianServiceImpl(LibrarianRepository repository) {
        super(repository);
    }
}

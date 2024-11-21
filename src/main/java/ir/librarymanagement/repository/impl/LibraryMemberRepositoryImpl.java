package ir.librarymanagement.repository.impl;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.LibraryMember;
import ir.librarymanagement.repository.LibraryMemberRepository;
import ir.librarymanagement.repository.base.BaseRepositoryImpl;

public class LibraryMemberRepositoryImpl extends BaseRepositoryImpl<LibraryMember, Long> implements LibraryMemberRepository {
    @Override
    protected Class<LibraryMember> getEntityType() {
        return LibraryMember.class;
    }

}

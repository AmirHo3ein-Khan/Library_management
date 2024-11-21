package ir.librarymanagement.service;

import ir.librarymanagement.model.Book;
import ir.librarymanagement.model.LibraryMember;
import ir.librarymanagement.service.base.BaseService;

public interface LibraryMemberService extends BaseService<LibraryMember , Long> {
    void borrowBook(LibraryMember libraryMember , Book book);
    void returnBook(LibraryMember libraryMember , Book book);
}

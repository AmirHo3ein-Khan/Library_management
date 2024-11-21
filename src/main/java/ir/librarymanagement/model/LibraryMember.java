package ir.librarymanagement.model;

import ir.librarymanagement.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class LibraryMember extends User {

    @OneToMany(mappedBy = "borrowedBookBy" , cascade = CascadeType.PERSIST)
    private List<Book> borrowedBooks;


    @Override
    public String toString() {
        return getUsername();
    }
}

package ir.librarymanagement.model;

import ir.librarymanagement.model.base.BaseModel;
import ir.librarymanagement.model.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseModel<Long> {

    private String subject;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne
    private LibraryMember borrowedBookBy;

    @Override
    public String toString() {
        return String.format("%-5d |%-12s\n",
                getId() , subject);
    }
}

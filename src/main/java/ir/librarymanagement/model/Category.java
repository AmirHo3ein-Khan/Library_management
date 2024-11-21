package ir.librarymanagement.model;

import ir.librarymanagement.model.base.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Category extends BaseModel<Long> {

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Book> books;

    @Override
    public String toString() {
        return String.format("%-5d |%-12s",
                getId() , name);
    }
}

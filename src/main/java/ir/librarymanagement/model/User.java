package ir.librarymanagement.model;

import ir.librarymanagement.model.base.BaseModel;
import ir.librarymanagement.model.enums.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
@SuperBuilder
@Table(name = "library_user")
public class User extends BaseModel<Long> {

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}

package bg.bulgarlegacy.model.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book_authors")
public class BookAuthorEntity extends BaseEntity {

    @NotNull
    @Size(min = 2,max = 20)
    private String firstName;

    @NotNull
    @Size(min = 2,max = 20)
    private String lastName;


}

package bg.bulgarlegacy.model.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {


    @NotNull
    @Column(unique = true)
    private String title;

    @NotNull
    private String imageUrl;

    @NotNull
    @ManyToOne
    private BookAuthorEntity bookAuthor;
}

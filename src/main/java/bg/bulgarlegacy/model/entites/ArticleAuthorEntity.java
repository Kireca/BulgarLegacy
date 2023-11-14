package bg.bulgarlegacy.model.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "article_authors")
public class ArticleAuthorEntity extends BaseEntity {


    private String firstName;

    @NotNull
    private String lastName;



}

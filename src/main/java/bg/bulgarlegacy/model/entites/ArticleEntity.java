package bg.bulgarlegacy.model.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String article;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @NotNull
    @PastOrPresent
    private LocalDate published;

    @NotNull
    @ManyToOne
    private ArticleAuthorEntity author;


}

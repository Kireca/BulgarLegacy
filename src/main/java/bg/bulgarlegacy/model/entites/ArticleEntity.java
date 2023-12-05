package bg.bulgarlegacy.model.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    private String content;

    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;


    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @NotNull
    @PastOrPresent
    private LocalDate published;

    @NotNull
    @ManyToOne
    private UserEntity author;

    @OneToMany(mappedBy = "article")
    private List<CommentEntity> comments;

}

package bg.bulgarlegacy.model.entites;

import bg.bulgarlegacy.model.enums.GenreEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @NotNull
    @Column(unique = true)
    @Size(min = 3 , max = 100)
    private String title;

    @NotNull
    @ManyToOne
    private BookAuthorEntity author;

    @NotNull
    @Positive
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenreEnum genre;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;
}

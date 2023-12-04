package bg.bulgarlegacy.model.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    private String title;

    @NotNull
    @ManyToOne
    private BookAuthorEntity author;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;
}

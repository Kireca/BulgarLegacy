package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import bg.bulgarlegacy.model.enums.GenreEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CreateBookDTO {

    private UUID uuid;

    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 100)
    private String title;

    @NotNull
    private String authorFirstName;

    @NotNull
    private String authorLastName;

    @NotNull
    @Positive
    private BigDecimal price;


    @NotNull
    private GenreEnum genre;

    @NotNull
    private String imageUrl;

}

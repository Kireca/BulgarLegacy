package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.enums.GenreEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

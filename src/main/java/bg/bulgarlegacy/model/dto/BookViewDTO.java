package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import bg.bulgarlegacy.model.enums.GenreEnum;
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
public class BookViewDTO {

    private UUID uuid;

    private String title;

    private BookAuthorEntity author;

    private BigDecimal price;

    private GenreEnum genre;

    private String imageUrl;


    public String authorFullName() {
        if (author.getFirstName().isEmpty()) {
            return author.getLastName();
        } else {
            return author.getFirstName() + " " + author.getLastName();
        }
    }
}

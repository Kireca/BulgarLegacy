package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import bg.bulgarlegacy.model.enums.GenreEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookViewDTOTest {

    private BookViewDTO bookViewDTO;

    @BeforeEach
    public void setup() {
        bookViewDTO = new BookViewDTO();
    }

    @Test
    public void testGettersAndSetters() {
        UUID uuid = UUID.randomUUID();
        String title = "Sample Book Title";
        BookAuthorEntity author = new BookAuthorEntity("John", "Doe");
        BigDecimal price = BigDecimal.valueOf(19.99);
        GenreEnum genre = GenreEnum.ИСТОРИЯ;
        String imageUrl = "sample_image.jpg";

        bookViewDTO.setUuid(uuid);
        bookViewDTO.setTitle(title);
        bookViewDTO.setAuthor(author);
        bookViewDTO.setPrice(price);
        bookViewDTO.setGenre(genre);
        bookViewDTO.setImageUrl(imageUrl);

        assertEquals(uuid, bookViewDTO.getUuid());
        assertEquals(title, bookViewDTO.getTitle());
        assertEquals(author, bookViewDTO.getAuthor());
        assertEquals(price, bookViewDTO.getPrice());
        assertEquals(genre, bookViewDTO.getGenre());
        assertEquals(imageUrl, bookViewDTO.getImageUrl());
    }

    @Test
    public void testAuthorFullName() {
        BookAuthorEntity author = mock(BookAuthorEntity.class);
        when(author.getFirstName()).thenReturn("Jane");
        when(author.getLastName()).thenReturn("Smith");

        bookViewDTO.setAuthor(author);

        assertEquals("Jane Smith", bookViewDTO.authorFullName());

        when(author.getFirstName()).thenReturn("");
        assertEquals("Smith", bookViewDTO.authorFullName());

        when(author.getFirstName()).thenReturn("Robi");
        when(author.getLastName()).thenReturn("Nikolov");
        assertEquals("Robi Nikolov", bookViewDTO.authorFullName());
    }
}

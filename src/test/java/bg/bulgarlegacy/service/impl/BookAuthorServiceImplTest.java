package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import bg.bulgarlegacy.repository.BookAuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookAuthorServiceImplTest {

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @InjectMocks
    private BookAuthorServiceImpl bookAuthorService;

    public BookAuthorServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthor() {
        // Given
        String firstName = "John";
        String lastName = "Doe";

        BookAuthorEntity mockAuthor = new BookAuthorEntity(firstName, lastName);
        when(bookAuthorRepository.save(any())).thenReturn(mockAuthor);

        // When
        BookAuthorEntity createdAuthor = bookAuthorService.createAuthor(firstName, lastName);

        // Then
        // Verify that the save method was called with the expected arguments
        ArgumentCaptor<BookAuthorEntity> authorCaptor = ArgumentCaptor.forClass(BookAuthorEntity.class);
        verify(bookAuthorRepository, times(1)).save(authorCaptor.capture());

        BookAuthorEntity capturedAuthor = authorCaptor.getValue();
        assertEquals(firstName, capturedAuthor.getFirstName());
        assertEquals(lastName, capturedAuthor.getLastName());

        // Verify that the returned author matches the mocked entity
        assertEquals(mockAuthor, createdAuthor);
    }
}

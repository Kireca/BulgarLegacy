package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.model.dto.CreateBookDTO;
import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import bg.bulgarlegacy.model.entites.BookEntity;
import bg.bulgarlegacy.model.enums.GenreEnum;
import bg.bulgarlegacy.repository.BookRepository;
import bg.bulgarlegacy.service.BookAuthorService;
import org.hibernate.annotations.UuidGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookAuthorService bookAuthorService;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Mocking the behavior of bookRepository
        List<BookEntity> books = Arrays.asList(new BookEntity(), new BookEntity());
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(books));

        // Call the method to be tested
        Page<BookViewDTO> result = bookService.getAllBooks(Pageable.unpaged());

        // Assertions
        assertEquals(2, result.getContent().size());
        // You can add more assertions based on your specific requirements
    }

    @Test
    void testGetBookDetail() {
        UUID bookUuid = UUID.randomUUID();
        BookEntity bookEntity = new BookEntity();
        when(bookRepository.findByUuid(bookUuid)).thenReturn(Optional.of(bookEntity));

        Optional<BookViewDTO> result = bookService.getBookDetail(bookUuid);

        assertEquals(bookEntity.getUuid(), result.orElseThrow().getUuid());
        // Add more assertions if needed
    }

    @Test
    void testCreateBook() {
        CreateBookDTO createBookDTO = new CreateBookDTO();
        createBookDTO.setTitle("Book Title");
        createBookDTO.setAuthorFirstName("Author First");
        createBookDTO.setAuthorLastName("Author Last");
        createBookDTO.setPrice(BigDecimal.valueOf(19.99));
        createBookDTO.setGenre(GenreEnum.БИОГРАФИЯ);
        createBookDTO.setImageUrl("book.jpg");

        BookEntity bookEntity = new BookEntity();
        bookEntity.setUuid(UUID.randomUUID());
        bookEntity.setTitle(createBookDTO.getTitle());
        bookEntity.setPrice(createBookDTO.getPrice());
        bookEntity.setGenre(createBookDTO.getGenre());
        bookEntity.setImageUrl(createBookDTO.getImageUrl());



        when(bookAuthorService.createAuthor(anyString(), anyString())).thenReturn(new BookAuthorEntity());
        when(bookRepository.save(any())).thenReturn(bookEntity);

        UUID result = bookService.createBook(createBookDTO);

        assertEquals(bookEntity.getUuid(), result);
        // Add more assertions if needed
    }

    @Test
    void testDeleteBook() {
        UUID bookUuid = UUID.randomUUID();
        bookService.deleteBook(bookUuid);
        verify(bookRepository, times(1)).deleteByUuid(bookUuid);
    }

    // Add more tests for other methods and edge cases

}

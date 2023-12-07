package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BooksControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private Model model;

    @Mock
    private Page<BookViewDTO> bookViewDTOPage;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAll() {
        // Mock data
        List<BookViewDTO> bookList = new ArrayList<>();
        BookViewDTO book1 = new BookViewDTO(/* Add required fields */);
        BookViewDTO book2 = new BookViewDTO(/* Add required fields */);
        bookList.add(book1);
        bookList.add(book2);

        // Mock the behavior of the bookService
        when(bookService.getAllBooks(any(Pageable.class))).thenReturn(bookViewDTOPage);
        when(bookViewDTOPage.getContent()).thenReturn(bookList);

        // Test the controller method
        String viewName = booksController.all(model, Pageable.unpaged());

        // Verify that the model has been populated with the book list attribute
        verify(model).addAttribute("books", bookViewDTOPage);

        // Assert the view name returned by the controller
        // Change "books" to the actual expected view name
        assert "books".equals(viewName);
    }
}

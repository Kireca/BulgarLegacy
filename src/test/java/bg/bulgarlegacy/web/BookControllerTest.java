package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.model.dto.CreateBookDTO;
import bg.bulgarlegacy.model.enums.GenreEnum;
import bg.bulgarlegacy.service.BookService;
import bg.bulgarlegacy.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPage() {
        assertEquals("book-add", bookController.add(model));
    }

    @Test
    void testAddBookSuccess() {
        CreateBookDTO createBookDTO = new CreateBookDTO();
        UUID newOfferUUID = UUID.randomUUID();
        when(bookService.createBook(createBookDTO)).thenReturn(newOfferUUID);

        assertEquals("redirect:/book/" + newOfferUUID, bookController.add(createBookDTO, bindingResult, redirectAttributes));

        verify(bookService, times(1)).createBook(createBookDTO);
    }

    @Test
    void testAddBookValidationFailure() {
        CreateBookDTO createBookDTO = new CreateBookDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals("redirect:/book/add", bookController.add(createBookDTO, bindingResult, redirectAttributes));

        verify(redirectAttributes, times(1)).addFlashAttribute("createBookDTO", createBookDTO);
        verify(redirectAttributes, times(1)).addFlashAttribute("org.springframework.validation.BindingResult.createBookDTO", bindingResult);
    }

    @Test
    void testDetails() {
        UUID uuid = UUID.randomUUID();
        BookViewDTO bookViewDTO = new BookViewDTO();
        when(bookService.getBookDetail(uuid)).thenReturn(Optional.of(bookViewDTO));

        assertEquals("details", bookController.details(uuid, model));

        verify(model, times(1)).addAttribute("bookDetail", bookViewDTO);
    }

    @Test
    void testDetailsNotFound() {
        UUID uuid = UUID.randomUUID();
        when(bookService.getBookDetail(uuid)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> bookController.details(uuid, model));
    }

    @Test
    void testDelete() {
        UUID uuid = UUID.randomUUID();

        assertEquals("redirect:/books/all", bookController.delete(uuid));

        verify(bookService, times(1)).deleteBook(uuid);
    }

    @Test
    void testModelAttributeGenres() {
        GenreEnum[] genres = GenreEnum.values();

        GenreEnum[] returnedGenres = bookController.genres();

        assertEquals(genres.length, returnedGenres.length);
    }
}

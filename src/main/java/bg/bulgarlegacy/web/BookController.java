package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.model.dto.CreateBookDTO;
import bg.bulgarlegacy.model.enums.GenreEnum;
import bg.bulgarlegacy.service.BookService;

import bg.bulgarlegacy.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("createBookDTO")) {
            model.addAttribute("createBookDTO", new CreateBookDTO());
        }
        return "book-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateBookDTO createBookDTO,
                      BindingResult bindingResult,
                      RedirectAttributes rAtt) {


        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createBookDTO", createBookDTO);
            rAtt.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createBookDTO", bindingResult);
            return "redirect:/book/add";
        }
        UUID newOfferUUID = bookService.createBook(createBookDTO);

        return "redirect:/book/" + newOfferUUID;
    }

    @ModelAttribute("genres")
    public GenreEnum[] genres() {
        return GenreEnum.values();
    }


    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model) {


        BookViewDTO bookViewDTO = bookService.getBookDetail(uuid)
                .orElseThrow(() -> new ObjectNotFoundException("Book with uuid " + uuid + " was not found!"));

        model.addAttribute("bookDetail", bookViewDTO);

        return "details";
    }

    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid) {
        bookService.deleteBook(uuid);

        return "redirect:/books/all";


    }


}

package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.service.BookService;

import bg.bulgarlegacy.service.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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

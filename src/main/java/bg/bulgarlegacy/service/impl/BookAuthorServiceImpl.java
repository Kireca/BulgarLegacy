package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import bg.bulgarlegacy.repository.BookAuthorRepository;
import bg.bulgarlegacy.service.BookAuthorService;
import org.springframework.stereotype.Service;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public BookAuthorEntity createAuthor(String firstName, String lastName) {
        BookAuthorEntity author = new BookAuthorEntity(firstName, lastName);
        return bookAuthorRepository.save(author);
    }
}

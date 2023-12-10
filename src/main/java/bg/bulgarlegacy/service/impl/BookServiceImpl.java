package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.model.dto.CreateBookDTO;
import bg.bulgarlegacy.model.entites.BookEntity;
import bg.bulgarlegacy.repository.BookRepository;
import bg.bulgarlegacy.service.BookAuthorService;
import bg.bulgarlegacy.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookAuthorService bookAuthorService;

    public BookServiceImpl(BookRepository bookRepository, BookAuthorService bookAuthorService) {
        this.bookRepository = bookRepository;
        this.bookAuthorService = bookAuthorService;
    }


    @Override
    public Page<BookViewDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(BookServiceImpl::mapAsView);
    }

    @Override
    public Optional<BookViewDTO> getBookDetail(UUID bookUuid) {
        return bookRepository.findByUuid(bookUuid)
                .map(BookServiceImpl::mapAsView);
    }


    @Override
    @Transactional
    public void deleteBook(UUID bookUuid) {
        bookRepository.deleteByUuid(bookUuid);
    }

    @Override
    public UUID createBook(CreateBookDTO createBookDTO) {

        BookEntity newBook = map(createBookDTO);
        newBook = bookRepository.save(newBook);
        return newBook.getUuid();
    }


    private BookEntity map(CreateBookDTO createBookDTO) {
        BookEntity book = new BookEntity();

        book.setUuid(UUID.randomUUID());
        book.setTitle(createBookDTO.getTitle());
        book.setAuthor(bookAuthorService.createAuthor(createBookDTO.getAuthorFirstName(), createBookDTO.getAuthorLastName()));
        book.setPrice(createBookDTO.getPrice());
        book.setGenre(createBookDTO.getGenre());
        book.setImageUrl(createBookDTO.getImageUrl());

        return book;
    }


    private static BookViewDTO mapAsView(BookEntity bookEntity) {

        BookViewDTO bookViewDTO = new BookViewDTO();
        bookViewDTO.setImageUrl(bookEntity.getImageUrl());
        bookViewDTO.setPrice(bookEntity.getPrice());
        bookViewDTO.setTitle(bookEntity.getTitle());
        bookViewDTO.setUuid(bookEntity.getUuid());
        bookViewDTO.setAuthor(bookEntity.getAuthor());
        bookViewDTO.setGenre(bookEntity.getGenre());

        return bookViewDTO;
    }


}

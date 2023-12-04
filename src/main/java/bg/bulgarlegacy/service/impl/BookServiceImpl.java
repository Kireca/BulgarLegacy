package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import bg.bulgarlegacy.model.entites.BookEntity;
import bg.bulgarlegacy.repository.BookRepository;
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

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
    public void deleteBook(UUID offerUUID) {
        bookRepository.deleteByUuid(offerUUID);
    }




    private static BookViewDTO mapAsView(BookEntity bookEntity) {

        BookViewDTO bookViewDTO = new BookViewDTO();
        bookViewDTO.setImageUrl(bookEntity.getImageUrl());
        bookViewDTO.setPrice(bookEntity.getPrice());
        bookViewDTO.setTitle(bookEntity.getTitle());
        bookViewDTO.setUuid(bookEntity.getUuid());
        bookViewDTO.setAuthor(bookEntity.getAuthor());

        return bookViewDTO;
    }


}

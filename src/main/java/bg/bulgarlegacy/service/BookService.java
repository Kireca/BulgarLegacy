package bg.bulgarlegacy.service;

import bg.bulgarlegacy.model.dto.BookViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface BookService {


    Page<BookViewDTO> getAllBooks(Pageable pageable);

    Optional<BookViewDTO> getBookDetail(UUID uuid);

    void deleteBook(UUID bookUuid);
}

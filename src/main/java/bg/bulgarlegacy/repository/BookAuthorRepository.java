package bg.bulgarlegacy.repository;

import bg.bulgarlegacy.model.entites.BookAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, Long> {
}

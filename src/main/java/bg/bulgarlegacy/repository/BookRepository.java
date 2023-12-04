package bg.bulgarlegacy.repository;

import bg.bulgarlegacy.model.entites.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {


    Optional<BookEntity> findByUuid(UUID bookUuid);


    void deleteByUuid(UUID bookUuid);
}

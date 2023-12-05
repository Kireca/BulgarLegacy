package bg.bulgarlegacy.repository;

import bg.bulgarlegacy.model.entites.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Page<CommentEntity> findAllByArticleId(Long id, Pageable pageable);

    void deleteAllByArticleId(Long articleID);

    void deleteById(Long id);
}

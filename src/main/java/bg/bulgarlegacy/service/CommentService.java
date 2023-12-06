package bg.bulgarlegacy.service;

import bg.bulgarlegacy.model.dto.CommentViewDTO;
import bg.bulgarlegacy.model.dto.CreateCommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {


    Page<CommentViewDTO> getAllCommentsByArticleUuid(UUID uuid, Pageable pageable);

    void deleteCurrentArticleComments(UUID uuid);

    void deleteCommentFromArticleById(Long id);

    void createComment(CreateCommentDTO createCommentDTO, UUID articleUUID, Long authorID);
}

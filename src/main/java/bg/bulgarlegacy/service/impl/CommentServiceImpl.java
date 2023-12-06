package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.CommentViewDTO;
import bg.bulgarlegacy.model.dto.CreateCommentDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.CommentEntity;
import bg.bulgarlegacy.repository.ArticleRepository;
import bg.bulgarlegacy.repository.CommentRepository;
import bg.bulgarlegacy.repository.UserRepository;
import bg.bulgarlegacy.service.CommentService;
import bg.bulgarlegacy.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<CommentViewDTO> getAllCommentsByArticleUuid(UUID uuid, Pageable pageable) {
        ArticleEntity article = articleRepository.findByUuid(uuid).orElseThrow();
        Page<CommentEntity> allByArticleId = commentRepository.findAllByArticleId(article.getId(), pageable);

        return allByArticleId.map(CommentServiceImpl::mapAsView);
    }

    @Override
    @Transactional
    public void deleteCurrentArticleComments(UUID uuid) {
        ArticleEntity articleEntity = articleRepository.findByUuid(uuid).orElseThrow();
        Long articleID = articleEntity.getId();
        commentRepository.deleteAllByArticleId(articleID);
    }

    @Override
    @Transactional
    public void deleteCommentFromArticleById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void createComment(CreateCommentDTO createCommentDTO, UUID articleUUID, Long authorID) {

        CommentEntity newComment = map(createCommentDTO, articleUUID, authorID);
        commentRepository.save(newComment);

    }

    private CommentEntity map(CreateCommentDTO createCommentDTO, UUID articleUUID, Long authorID) {
        CommentEntity newComment = new CommentEntity();

        newComment.setContent(createCommentDTO.getContent());
        newComment.setPublished(LocalDateTime.now());
        newComment.setArticle(articleRepository.findByUuid(articleUUID).orElseThrow());
        newComment.setAuthor(userRepository.findById(authorID).orElseThrow());
        return newComment;
    }


    private static CommentViewDTO mapAsView(CommentEntity comment) {
        CommentViewDTO newComment = new CommentViewDTO();

        newComment.setId(comment.getId());
        newComment.setArticle(comment.getArticle());
        newComment.setContent(comment.getContent());
        newComment.setPublished(comment.getPublished());
        newComment.setAuthor(comment.getAuthor());

        return newComment;
    }
}

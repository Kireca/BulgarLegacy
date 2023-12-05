package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.CommentViewDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.CommentEntity;
import bg.bulgarlegacy.repository.ArticleRepository;
import bg.bulgarlegacy.repository.CommentRepository;
import bg.bulgarlegacy.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public Page<CommentViewDTO> getAllCommentsByArticleUuid(UUID uuid, Pageable pageable) {
        ArticleEntity article = articleRepository.findByUuid(uuid).orElseThrow();
        Page<CommentEntity> allByArticleId = commentRepository.findAllByArticleId(article.getId(), pageable);

        return allByArticleId.map(CommentServiceImpl::map);
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


    private static CommentViewDTO map(CommentEntity comment) {
        CommentViewDTO newComment = new CommentViewDTO();

        newComment.setId(comment.getId());
        newComment.setArticle(comment.getArticle());
        newComment.setContent(comment.getContent());
        newComment.setPublished(comment.getPublished());
        newComment.setAuthor(comment.getAuthor());

        return newComment;
    }
}

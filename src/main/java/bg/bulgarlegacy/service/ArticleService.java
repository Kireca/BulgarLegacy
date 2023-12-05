package bg.bulgarlegacy.service;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ArticleService {
    Page<ArticleViewDTO> getAllArticles(Pageable pageable);

    Optional<ArticleViewDTO> getArticleDetail(UUID uuid);

    UUID createArticle(CreateArticleDTO createArticleDTO);

    void deleteArticle(UUID articleUuid);

    ArticleEntity getArticleByUuid(UUID uuid);

}

package bg.bulgarlegacy.service;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ArticleService {
    Page<ArticleViewDTO> getAllArticles(Pageable pageable);

    Optional<ArticleViewDTO> getArticleDetail(UUID uuid);
}

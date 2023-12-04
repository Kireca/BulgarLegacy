package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.repository.ArticleRepository;
import bg.bulgarlegacy.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override
    public Page<ArticleViewDTO> getAllArticles(Pageable pageable) {
        return articleRepository.findAll(pageable)
                .map(ArticleServiceImpl::mapAsView);
    }

    @Override
    public Optional<ArticleViewDTO> getArticleDetail(UUID uuid) {
        return articleRepository.findByUuid(uuid)
                .map(ArticleServiceImpl::mapAsView);
    }


    private static ArticleViewDTO mapAsView(ArticleEntity articleEntity) {
        ArticleViewDTO mappedArticle = new ArticleViewDTO();

        mappedArticle.setContent(articleEntity.getContent());
        mappedArticle.setImageUrl(articleEntity.getImageUrl());
        mappedArticle.setPublished(articleEntity.getPublished());
        mappedArticle.setTitle(articleEntity.getTitle());
        mappedArticle.setUuid(articleEntity.getUuid());
        mappedArticle.setAuthor(articleEntity.getAuthor());

        return mappedArticle;

    }

}

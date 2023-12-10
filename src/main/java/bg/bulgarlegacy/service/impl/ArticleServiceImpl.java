package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.repository.ArticleRepository;
import bg.bulgarlegacy.service.ArticleService;
import bg.bulgarlegacy.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository articleRepository;
    private final UserService userService;


    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
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

    @Override
    public UUID createArticle(CreateArticleDTO createArticleDTO) {


        ArticleEntity newArticleEntity = map(createArticleDTO);

        articleRepository.save(newArticleEntity);

        return newArticleEntity.getUuid();

    }

    @Override
    @Transactional
    public void deleteArticle(UUID articleUuid) {
        articleRepository.deleteByUuid(articleUuid);
    }

    @Override
    public ArticleEntity getArticleByUuid(UUID uuid) {
        return articleRepository.findByUuid(uuid).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteAllArticles() {
        articleRepository.deleteAll();
    }


    ArticleEntity map(CreateArticleDTO createArticleDTO) {
        ArticleEntity newArticle = new ArticleEntity();

        UserEntity currentUser = userService.getCurrentUser();

        newArticle.setUuid(UUID.randomUUID());
        newArticle.setAuthor(currentUser);
        newArticle.setPublished(LocalDate.now());
        newArticle.setContent(createArticleDTO.getContent());
        newArticle.setTitle(createArticleDTO.getTitle());
        newArticle.setImageUrl(createArticleDTO.getImageUrl());


        return articleRepository.save(newArticle);
    }


    static ArticleViewDTO mapAsView(ArticleEntity articleEntity) {
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

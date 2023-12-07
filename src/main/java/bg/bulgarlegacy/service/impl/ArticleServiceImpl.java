package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.repository.ArticleRepository;
import bg.bulgarlegacy.repository.UserRepository;
import bg.bulgarlegacy.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;



    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
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


    ArticleEntity map(CreateArticleDTO createArticleDTO) {
        ArticleEntity newArticle = new ArticleEntity();

        UserEntity currentUser = getCurrentUser();


        newArticle.setUuid(UUID.randomUUID());
        newArticle.setAuthor(currentUser);
        newArticle.setPublished(LocalDate.now());
        newArticle.setContent(createArticleDTO.getContent());
        newArticle.setTitle(createArticleDTO.getTitle());
        newArticle.setImageUrl(createArticleDTO.getImageUrl());


        return articleRepository.save(newArticle);
    }

    private UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUserEmail = userDetails.getUsername();
        return userRepository.findByEmail(currentUserEmail).orElseThrow();
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

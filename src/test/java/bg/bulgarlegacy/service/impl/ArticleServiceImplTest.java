package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.repository.ArticleRepository;
import bg.bulgarlegacy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllArticles_ReturnsArticleViewDTOPage() {

        Page<ArticleEntity> articleEntityPage = mock(Page.class);
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(articleEntityPage);


        Page<ArticleViewDTO> result = articleService.getAllArticles(Pageable.unpaged());


        assertEquals(articleEntityPage.map(ArticleServiceImpl::mapAsView), result);
        verify(articleRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getArticleDetail_ReturnsArticleViewDTO() {
        UUID uuid = UUID.randomUUID();
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setUuid(uuid);
        when(articleRepository.findByUuid(uuid)).thenReturn(Optional.of(articleEntity));

        Optional<ArticleViewDTO> result = articleService.getArticleDetail(uuid);

        assertTrue(result.isPresent());
        assertEquals(articleEntity.getUuid(), result.get().getUuid());
        verify(articleRepository, times(1)).findByUuid(uuid);
    }

    @Test
    void createArticle_CreatesArticleAndReturnsUUID() {

        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        ArticleEntity newArticleEntity = new ArticleEntity();
        newArticleEntity.setUuid(UUID.randomUUID());

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = User.withUsername("testuser").password("password").roles("USER").build();
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new UserEntity()));


        UUID result = articleService.createArticle(createArticleDTO);


        assertNotNull(result);
        verify(articleRepository, times(1)).save(any());
    }

    @Test
    void deleteArticle_DeletesArticle() {

        UUID articleUuid = UUID.randomUUID();


        articleService.deleteArticle(articleUuid);


        verify(articleRepository, times(1)).deleteByUuid(articleUuid);
    }

    @Test
    void getArticleByUuid_ReturnsArticleEntity() {

        UUID uuid = UUID.randomUUID();
        ArticleEntity articleEntity = new ArticleEntity();
        when(articleRepository.findByUuid(uuid)).thenReturn(Optional.of(articleEntity));

        // Act
        ArticleEntity result = articleService.getArticleByUuid(uuid);


        assertNotNull(result);
        verify(articleRepository, times(1)).findByUuid(uuid);
    }

}

package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articleService = new ArticleServiceImpl(articleRepository, userRepository);
    }

    @Test
    void getAllArticles_ReturnsPageOfArticleViewDTO() {
        // Mocking data
        Pageable pageable = mock(Pageable.class);
        ArticleEntity articleEntity = createMockArticleEntity();
        Page<ArticleEntity> articleEntities = new PageImpl<>(Collections.singletonList(articleEntity));
        when(articleRepository.findAll(pageable)).thenReturn(articleEntities);

        // Test
        Page<ArticleViewDTO> result = articleService.getAllArticles(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        // Add more assertions as needed based on the expected behavior
    }

    @Test
    void getArticleDetail_ReturnsOptionalArticleViewDTO() {
        // Mocking data
        UUID uuid = UUID.randomUUID();
        ArticleEntity articleEntity = createMockArticleEntity();
        when(articleRepository.findByUuid(uuid)).thenReturn(Optional.of(articleEntity));

        // Test
        Optional<ArticleViewDTO> result = articleService.getArticleDetail(uuid);

        assertTrue(result.isPresent());
        // Add more assertions as needed based on the expected behavior
    }

    // Helper method to create a mock ArticleEntity
    private ArticleEntity createMockArticleEntity() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setUuid(UUID.randomUUID());
        articleEntity.setAuthor(new UserEntity());
        articleEntity.setPublished(LocalDate.now());
        articleEntity.setContent("Sample Content");
        articleEntity.setTitle("Sample Title");
        articleEntity.setImageUrl("sample-image-url");
        return articleEntity;
    }

    // Write similar tests for other methods like createArticle, deleteArticle, etc.
    // Mock necessary dependencies and verify method behaviors as expected
}

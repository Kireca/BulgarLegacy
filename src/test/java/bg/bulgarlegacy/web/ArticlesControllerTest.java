package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.entites.CommentEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ExtendedModelMap;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ArticlesControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticlesController articlesController;

    @Test
    public void testAllWithArticles() {
        // Mock data when articles exist
        ArticleViewDTO article1 = new ArticleViewDTO(
                "Title",
                "Content",
                "imageURL/asdasd",
                LocalDate.now(),
                UUID.randomUUID(),
                new UserEntity(),List.of(new CommentEntity(),new CommentEntity()));
        ArticleViewDTO article2 = new ArticleViewDTO(
                "Title1",
                "Content1",
                "imageURL/asdasd1",
                LocalDate.now(),
                UUID.randomUUID(),
                new UserEntity(),List.of(new CommentEntity(),new CommentEntity()));

        Page<ArticleViewDTO> articlesPage = new PageImpl<>(List.of(article1, article2));

        when(articleService.getAllArticles(any(Pageable.class))).thenReturn(articlesPage);

        ExtendedModelMap model = new ExtendedModelMap();
        String viewName = articlesController.all(model, PageRequest.of(0, 8));

        assertEquals("articles", viewName);

        Page<ArticleViewDTO> modelAttribute = (Page<ArticleViewDTO>) model.get("articles");
        assertEquals(articlesPage, modelAttribute);
    }

    @Test
    public void testAllWithNoArticles() {
        // Mock data when no articles exist
        Page<ArticleViewDTO> emptyArticlesPage = new PageImpl<>(Collections.emptyList());

        when(articleService.getAllArticles(any(Pageable.class))).thenReturn(emptyArticlesPage);

        ExtendedModelMap model = new ExtendedModelMap();
        String viewName = articlesController.all(model, PageRequest.of(0, 8));

        assertEquals("articles", viewName);

        Page<ArticleViewDTO> modelAttribute = (Page<ArticleViewDTO>) model.get("articles");
        assertTrue(modelAttribute.isEmpty());
    }

}

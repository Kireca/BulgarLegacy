package bg.bulgarlegacy.web;

import bg.bulgarlegacy.model.dto.ArticleViewDTO;
import bg.bulgarlegacy.model.dto.CreateArticleDTO;
import bg.bulgarlegacy.model.dto.CommentViewDTO;
import bg.bulgarlegacy.service.ArticleService;
import bg.bulgarlegacy.service.CommentService;
import bg.bulgarlegacy.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @Mock
    private CommentService commentService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ArticleController articleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddArticle() {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        UUID newArticleUUID = UUID.randomUUID();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(articleService.createArticle(createArticleDTO)).thenReturn(newArticleUUID);

        String redirectURL = articleController.add(createArticleDTO, bindingResult, redirectAttributes);

        assertEquals("redirect:/article/" + newArticleUUID, redirectURL);
        verify(articleService, times(1)).createArticle(createArticleDTO);
    }

    @Test
    void testAddArticleWithValidationErrors() {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        String redirectURL = articleController.add(createArticleDTO, bindingResult, redirectAttributes);

        assertEquals("redirect:/article/add", redirectURL);
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("createArticleDTO"), eq(createArticleDTO));
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("org.springframework.validation.BindingResult.createArticleDTO"), any(BindingResult.class));
        verifyNoInteractions(articleService);
    }

    @Test
    void testArticleDetails() {
        UUID articleUUID = UUID.randomUUID();
        ArticleViewDTO articleViewDTO = new ArticleViewDTO();
        List<CommentViewDTO> commentList = new ArrayList<>();
        Page<CommentViewDTO> commentPage = new PageImpl<>(commentList);

        when(articleService.getArticleDetail(articleUUID)).thenReturn(Optional.of(articleViewDTO));
        when(commentService.getAllCommentsByArticleUuid(articleUUID, Pageable.unpaged())).thenReturn(commentPage);

        String viewName = articleController.details(articleUUID, model, Pageable.unpaged());

        assertEquals("article-details", viewName);
        verify(articleService, times(1)).getArticleDetail(articleUUID);
        verify(commentService, times(1)).getAllCommentsByArticleUuid(articleUUID, Pageable.unpaged());
        verify(model, times(1)).addAttribute("articleDetails", articleViewDTO);
        verify(model, times(1)).addAttribute("comments", commentPage);
    }

    @Test
    void testArticleDetailsNotFound() {
        UUID articleUUID = UUID.randomUUID();
        when(articleService.getArticleDetail(articleUUID)).thenReturn(Optional.empty());

        try {
            articleController.details(articleUUID, model, Pageable.unpaged());
        } catch (ObjectNotFoundException e) {
            // Expected behavior
        }

        verify(articleService, times(1)).getArticleDetail(articleUUID);
        verifyNoInteractions(commentService);
        verifyNoInteractions(model);
    }

    @Test
    void testDelete() {
        UUID articleUUID = UUID.randomUUID();

        String redirectURL = articleController.deleteArticle(articleUUID);

        assertEquals("redirect:/articles/all", redirectURL);
        verify(commentService, times(1)).deleteCurrentArticleComments(articleUUID);
        verify(articleService, times(1)).deleteArticle(articleUUID);
    }

    // Additional tests for other methods can be added here
}

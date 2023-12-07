package bg.bulgarlegacy.model.entites;

import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.CommentEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleEntityTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGettersAndSetters() {
        ArticleEntity article = new ArticleEntity();
        String title = "Sample Title";
        String content = "Sample Content";
        String imageUrl = "https://example.com/image.png";
        LocalDate publishedDate = LocalDate.now();
        UUID uuid = UUID.randomUUID();
        UserEntity author = new UserEntity();
        List<CommentEntity> comments = Collections.emptyList();

        article.setTitle(title);
        article.setContent(content);
        article.setImageUrl(imageUrl);
        article.setPublished(publishedDate);
        article.setUuid(uuid);
        article.setAuthor(author);
        article.setComments(comments);

        assertEquals(title, article.getTitle());
        assertEquals(content, article.getContent());
        assertEquals(imageUrl, article.getImageUrl());
        assertEquals(publishedDate, article.getPublished());
        assertEquals(uuid, article.getUuid());
        assertEquals(author, article.getAuthor());
        assertEquals(comments, article.getComments());
    }

    @Test
    void testNotNullConstraints() {
        ArticleEntity article = new ArticleEntity();
        Set<ConstraintViolation<ArticleEntity>> violations = validator.validate(article);

        assertTrue(violations.stream().anyMatch(violation -> "title".equals(violation.getPropertyPath().toString())));
        assertTrue(violations.stream().anyMatch(violation -> "content".equals(violation.getPropertyPath().toString())));
        assertTrue(violations.stream().anyMatch(violation -> "uuid".equals(violation.getPropertyPath().toString())));
        assertTrue(violations.stream().anyMatch(violation -> "published".equals(violation.getPropertyPath().toString())));
        assertTrue(violations.stream().anyMatch(violation -> "author".equals(violation.getPropertyPath().toString())));
    }


}

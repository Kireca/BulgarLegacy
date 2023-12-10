package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.CommentEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArticleViewDTOTest {

    private ArticleViewDTO articleViewDTO;
    private UserEntity userEntity;
    private CommentEntity commentEntity;

    @BeforeEach
    public void setUp() {
        articleViewDTO = new ArticleViewDTO();
        articleViewDTO.setTitle("Title");
        articleViewDTO.setContent("Content");
        articleViewDTO.setImageUrl("image.jpg");
        articleViewDTO.setPublished(LocalDate.now());
        articleViewDTO.setUuid(UUID.randomUUID());

        userEntity = new UserEntity();
        userEntity.setUsername("username");
        articleViewDTO.setAuthor(userEntity);

        commentEntity = new CommentEntity();
        commentEntity.setContent("content");
        List<CommentEntity> comments = new ArrayList<>();
        comments.add(commentEntity);
        articleViewDTO.setCommentEntities(comments);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Title", articleViewDTO.getTitle());
        assertEquals("Content", articleViewDTO.getContent());
        assertEquals("image.jpg", articleViewDTO.getImageUrl());
        assertEquals(LocalDate.now(), articleViewDTO.getPublished());
        assertNotNull(articleViewDTO.getUuid());
        assertEquals("username", articleViewDTO.getAuthorUsername());

        List<CommentEntity> comments = articleViewDTO.getCommentEntities();
        assertNotNull(comments);
        assertEquals(1, comments.size());
        assertEquals("content", comments.get(0).getContent());
    }
}

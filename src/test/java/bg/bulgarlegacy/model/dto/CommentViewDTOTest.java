package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentViewDTOTest {

    private CommentViewDTO commentViewDTO;
    private UserEntity userEntity;
    private ArticleEntity articleEntity;

    @BeforeEach
    void setUp() {
        commentViewDTO = new CommentViewDTO();
        userEntity = new UserEntity();
        articleEntity = new ArticleEntity();
    }

    @Test
    void testIdField() {
        Long expectedId = 1L;
        commentViewDTO.setId(expectedId);
        assertEquals(expectedId, commentViewDTO.getId());
    }

    @Test
    void testContentField() {
        String expectedContent = "Test content";
        commentViewDTO.setContent(expectedContent);
        assertEquals(expectedContent, commentViewDTO.getContent());
    }

    // Test the rest of the fields similarly...

    @Test
    void testGetAuthorUsername() {
        String expectedUsername = "testUser";
        userEntity.setUsername(expectedUsername);
        commentViewDTO.setAuthor(userEntity);
        assertEquals(expectedUsername, commentViewDTO.getAuthorUsername());
    }
}

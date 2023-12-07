package bg.bulgarlegacy.model.entites;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class CommentEntityTest {

    @Test
    public void commentEntity_SetAndGetContent() {
        CommentEntity comment = new CommentEntity();
        String content = "This is a test comment.";
        comment.setContent(content);
        assertEquals(content, comment.getContent());
    }

    @Test
    public void commentEntity_SetAndGetAuthor() {
        CommentEntity comment = new CommentEntity();
        UserEntity author = new UserEntity();
        comment.setAuthor(author);
        assertEquals(author, comment.getAuthor());
    }

    @Test
    public void commentEntity_SetAndGetArticle() {
        CommentEntity comment = new CommentEntity();
        ArticleEntity article = new ArticleEntity();
        comment.setArticle(article);
        assertEquals(article, comment.getArticle());
    }

    @Test
    public void commentEntity_SetAndGetPublished() {
        CommentEntity comment = new CommentEntity();
        LocalDateTime published = LocalDateTime.now();
        comment.setPublished(published);
        assertEquals(published, comment.getPublished());
    }
}

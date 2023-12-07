package bg.bulgarlegacy.model.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateArticleDTOTest {

    @Test
    public void createArticleDTO_WhenInstantiated_ValuesAreSetCorrectly() {

        String title = "Sample Title";
        String content = "Sample Content";
        String imageUrl = "https://sample-image.com";
        UUID uuid = UUID.randomUUID();
        LocalDate published = LocalDate.now();

        CreateArticleDTO createArticleDTO2 = new CreateArticleDTO(title,content,imageUrl,uuid,published);

        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        createArticleDTO.setTitle(title);
        createArticleDTO.setContent(content);
        createArticleDTO.setImageUrl(imageUrl);
        createArticleDTO.setUuid(uuid);
        createArticleDTO.setPublished(published);

        assertNotNull(createArticleDTO);
        assertNotNull(createArticleDTO2);
        assertEquals(title, createArticleDTO.getTitle());
        assertEquals(content, createArticleDTO.getContent());
        assertEquals(imageUrl, createArticleDTO.getImageUrl());
        assertEquals(uuid, createArticleDTO.getUuid());
        assertEquals(published, createArticleDTO.getPublished());
    }
}

package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.CommentEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArticleViewDTO {


    private String title;

    private String content;

    private String imageUrl;

    private LocalDate published;

    private UUID uuid;
    private UserEntity author;

    private List<CommentEntity> commentEntities;


    public String getAuthorUsername() {

        return author.getUsername();
    }

}

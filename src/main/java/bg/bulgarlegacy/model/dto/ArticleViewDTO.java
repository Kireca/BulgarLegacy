package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.Comment;
import bg.bulgarlegacy.model.entites.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    private List<Comment> comments;


    public String getAuthorUsername() {

        return author.getUsername();
    }

}

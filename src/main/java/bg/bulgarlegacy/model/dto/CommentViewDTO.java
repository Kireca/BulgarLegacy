package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentViewDTO {

    private Long id;

    @NotNull
    private String content;

    @NotNull
    private UserEntity author;

    @NotNull
    private ArticleEntity article;

    private LocalDateTime published;

    public String getAuthorUsername() {
        return this.author.getUsername();
    }

}

package bg.bulgarlegacy.model.dto;

import bg.bulgarlegacy.model.entites.ArticleEntity;
import bg.bulgarlegacy.model.entites.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateCommentDTO {


    private String content;

    private Long authorID;

    private UUID articleUUID;

    private LocalDateTime published;

}

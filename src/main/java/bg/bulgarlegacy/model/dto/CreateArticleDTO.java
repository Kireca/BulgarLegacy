package bg.bulgarlegacy.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateArticleDTO {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String imageUrl;

    private UUID uuid;

    private LocalDate published;


}

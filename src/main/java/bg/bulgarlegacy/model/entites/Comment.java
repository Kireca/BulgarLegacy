package bg.bulgarlegacy.model.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 100)
    private String content;

    @ManyToOne
    @NotNull
    private UserEntity author;

}

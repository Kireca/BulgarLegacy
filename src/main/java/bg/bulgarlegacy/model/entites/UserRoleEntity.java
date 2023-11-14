package bg.bulgarlegacy.model.entites;


import bg.bulgarlegacy.model.enums.UserRoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}

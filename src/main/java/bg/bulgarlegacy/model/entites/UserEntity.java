package bg.bulgarlegacy.model.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRoleEntity> roles;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private boolean active;

    @Column(nullable = false)
    @Size(min = 2,max = 20)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2,max = 20)
    private String lastName;

    @Column(nullable = false)
    private String password;

}

package bg.bulgarlegacy.model.dto;


import bg.bulgarlegacy.model.validation.FieldMatch;
import bg.bulgarlegacy.model.validation.UniqueUserEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor

@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords don't match!")
public class UserRegistrationDTO {

    @NotNull
    @Size(min = 2,max = 20)
    private String username;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;
}

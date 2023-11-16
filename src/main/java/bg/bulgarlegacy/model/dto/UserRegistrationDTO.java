package bg.bulgarlegacy.model.dto;


import bg.bulgarlegacy.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserRegistrationDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @UniqueUserEmail
    private String email;

    @NotNull

    private String password;

    @NotNull
    private String confirmPassword;
}

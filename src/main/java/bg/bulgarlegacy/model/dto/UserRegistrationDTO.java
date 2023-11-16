package bg.bulgarlegacy.model.dto;


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
    private String emailName;

    private String password;

    private String confirmPassword;
}

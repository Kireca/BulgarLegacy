package bg.bulgarlegacy.service;

import bg.bulgarlegacy.model.dto.UserRegistrationDTO;
import bg.bulgarlegacy.model.entites.UserEntity;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);

    UserEntity getCurrentUser();
}

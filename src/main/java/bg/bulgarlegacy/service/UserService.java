package bg.bulgarlegacy.service;

import bg.bulgarlegacy.model.dto.UserRegistrationDTO;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);
}

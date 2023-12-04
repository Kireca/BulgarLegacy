package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.UserRegistrationDTO;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.repository.UserRepository;
import bg.bulgarlegacy.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
    }


    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = new UserEntity();

        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setActive(false);
        return user;
    }
}

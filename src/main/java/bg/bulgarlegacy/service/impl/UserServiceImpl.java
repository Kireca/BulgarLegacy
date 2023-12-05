package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.UserRegistrationDTO;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.model.entites.UserRoleEntity;
import bg.bulgarlegacy.model.enums.UserRoleEnum;
import bg.bulgarlegacy.repository.UserRepository;
import bg.bulgarlegacy.repository.UserRoleRepository;
import bg.bulgarlegacy.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
    }


    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = new UserEntity();

        List<UserRoleEntity> roles = new ArrayList<>();
        UserRoleEntity role;

        if (userRepository.findAll().isEmpty()) {
            role = new UserRoleEntity(UserRoleEnum.ADMINISTRATOR);
        }else {
            role = new UserRoleEntity(UserRoleEnum.USER);
        }

        userRoleRepository.save(role);
        roles.add(role);


        user.setRoles(roles);
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setActive(false);
        return user;
    }
}

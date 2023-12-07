package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.dto.UserRegistrationDTO;
import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.model.entites.UserRoleEntity;
import bg.bulgarlegacy.model.enums.UserRoleEnum;
import bg.bulgarlegacy.repository.UserRepository;
import bg.bulgarlegacy.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("testuser");
        userRegistrationDTO.setEmail("test@example.com");


        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setUsername(userRegistrationDTO.getUsername());
        mockedUserEntity.setEmail(userRegistrationDTO.getEmail());


        UserRoleEntity userRoleEntity = new UserRoleEntity(UserRoleEnum.USER);
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRoleRepository.save(any())).thenReturn(userRoleEntity);
        when(passwordEncoder.encode(userRegistrationDTO.getPassword())).thenReturn("hashedPassword");
        when(userRepository.save(any())).thenReturn(mockedUserEntity);


        userService.registerUser(userRegistrationDTO);

        verify(userRepository, times(1)).save(any());

    }
}

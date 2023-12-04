package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    private UserServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        serviceToTest = new UserServiceImpl(mockUserRepository, mockPasswordEncoder);
    }


    @Test
    void testMock() {

        Optional<UserEntity> userOptional = mockUserRepository.findByEmail("test@example.com");
        Assertions.assertEquals(Optional.empty(),userOptional);

    }


}
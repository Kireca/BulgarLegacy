package bg.bulgarlegacy.service.impl;

import bg.bulgarlegacy.model.entites.UserEntity;
import bg.bulgarlegacy.model.entites.UserRoleEntity;
import bg.bulgarlegacy.model.enums.UserRoleEnum;
import bg.bulgarlegacy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

public class BulgarLegacyUserDetailsServiceTest {

  @Test
  public void testLoadUserByUsername_Success() {
    // Mock UserRepository
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    // Create a user entity
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail("test@example.com");
    userEntity.setPassword("password");

    // Create a role entity
    UserRoleEntity userRoleEntity = new UserRoleEntity();
    userRoleEntity.setRole(UserRoleEnum.USER);

    // Set roles for the user
    List<UserRoleEntity> roles = List.of(new UserRoleEntity(UserRoleEnum.USER));
    userEntity.setRoles(roles);

    // Mock the behavior of UserRepository's findByEmail method
    Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));

    // Create an instance of the service with the mocked repository
    BulgarLegacyUserDetailsService userDetailsService = new BulgarLegacyUserDetailsService(userRepository);

    // Call the loadUserByUsername method
    UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

    // Verify the returned UserDetails
    assertEquals("test@example.com", userDetails.getUsername());
    assertEquals("password", userDetails.getPassword());
    assertEquals(1, userDetails.getAuthorities().size());
  }

  @Test
  public void testLoadUserByUsername_UserNotFound() {
    // Mock UserRepository
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    // Mock the behavior of UserRepository's findByEmail method
    Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

    // Create an instance of the service with the mocked repository
    BulgarLegacyUserDetailsService userDetailsService = new BulgarLegacyUserDetailsService(userRepository);

    // Call the loadUserByUsername method with a non-existent user
    assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("nonexistent@example.com"));
  }
}

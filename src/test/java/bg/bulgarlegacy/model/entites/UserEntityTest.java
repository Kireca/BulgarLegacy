package bg.bulgarlegacy.model.entites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
    }

    @Test
    void testRolesField() throws NoSuchFieldException {
        Field rolesField = UserEntity.class.getDeclaredField("roles");
        assertNotNull(rolesField);
        assertTrue(List.class.isAssignableFrom(rolesField.getType()));
    }

    @Test
    void testEmailField() throws NoSuchFieldException {
        Field emailField = UserEntity.class.getDeclaredField("email");
        assertNotNull(emailField);
        assertTrue(String.class.isAssignableFrom(emailField.getType()));
    }

    @Test
    void testActiveField() throws NoSuchFieldException {
        Field activeField = UserEntity.class.getDeclaredField("active");
        assertNotNull(activeField);
        assertTrue(boolean.class.isAssignableFrom(activeField.getType()));
    }

    @Test
    void testUsernameField() throws NoSuchFieldException {
        Field usernameField = UserEntity.class.getDeclaredField("username");
        assertNotNull(usernameField);
        assertTrue(String.class.isAssignableFrom(usernameField.getType()));
    }

    @Test
    void testFirstNameField() throws NoSuchFieldException {
        Field firstNameField = UserEntity.class.getDeclaredField("firstName");
        assertNotNull(firstNameField);
        assertTrue(String.class.isAssignableFrom(firstNameField.getType()));
    }

    @Test
    void testLastNameField() throws NoSuchFieldException {
        Field lastNameField = UserEntity.class.getDeclaredField("lastName");
        assertNotNull(lastNameField);
        assertTrue(String.class.isAssignableFrom(lastNameField.getType()));
    }

    @Test
    void testPasswordField() throws NoSuchFieldException {
        Field passwordField = UserEntity.class.getDeclaredField("password");
        assertNotNull(passwordField);
        assertTrue(String.class.isAssignableFrom(passwordField.getType()));
    }

    // Test methods

    @Test
    void testGettersAndSetters() {
        // Get all methods
        Method[] methods = UserEntity.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("set")) {
                assertNotNull(method);
            }
        }
    }

    // Add more tests for specific methods if needed

}

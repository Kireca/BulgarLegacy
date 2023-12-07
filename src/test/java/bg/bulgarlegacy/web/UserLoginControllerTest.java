package bg.bulgarlegacy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

    @Test
    void testLoginError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login-error"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("email"))
                .andExpect(model().attributeExists("bad_credentials"))
                .andExpect(model().attribute("bad_credentials", "true"))
                .andExpect(view().name("auth-login"));
    }
}

package bg.bulgarlegacy.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {



    @Autowired
    private MockMvc mockMvc;


    @Test
    void testRegister() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", "TestFirstName")
                        .param("lastName","TestLastName")
                        .param("email","TestEmail@testEmail.com")
                        .param("username","TestUsername")
                        .param("password","passwordTest")
                        .param("confirmPassword","passwordTest")
                        .with(csrf())

        ).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/"));
    }

}
package projekti;

import org.fluentlenium.adapter.junit.FluentTest;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WelcomeControllerTest extends FluentTest {

    @Autowired
    private MockMvc mockMvc;

    // Not logged in
    // WelcomeController - TESTS
    // Testing that welcome.html contains certain text
    @Test
    public void getRequestToTermsOfServiceReturnsTermsOfServiceText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/TermsOfService")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("guarded-thicket-83287.herokuapp.com (the ")));
    }

    @Test
    public void getRequestToWelcomeReturnsWelcomeVisitorText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome, visitor!")));
    }

    @Test
    public void getRequestToWelcomeReturnsUserNotLoggedInText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User (Not logged in)")));
    }

    @Test
    public void getRequestToWelcomeReturnsSmallLogoText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("EmployeeSearch")));
    }

    @Test
    public void getRequestToWelcomeReturnsPromotionText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("As a Registered User you will get your own Home Page")));
    }

    @Test
    public void getRequestToWelcomeReturnsFooterText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("&copy; 2020 Copyright: Toni Silfver")));
    }
}

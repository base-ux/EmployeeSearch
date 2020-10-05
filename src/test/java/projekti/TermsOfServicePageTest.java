package projekti;

import static org.assertj.core.api.Assertions.assertThat;
import org.fluentlenium.adapter.junit.FluentTest;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
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
public class TermsOfServicePageTest extends FluentTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private Integer port;

    // WelcomeController - TESTS continued also in WelcomePageTest.java
    @Test
    public void getRequestToTermsOfServiceReturnsTermsOfServiceText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/TermsOfService")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("guarded-thicket-83287.herokuapp.com (the ")));
    }

    // terms_of_service.html - TESTS
    // Testing that links work
    @Test
    public void clickReturnLinkThenPageSourceContainsSignUp() {
        clickReturnLink();
    }

    public void clickReturnLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/TermsOfService");
        $("#backToRegisterPage").click();
        assertThat(pageSource()).contains("Sign up");
    }
}

package projekti;

import static org.assertj.core.api.Assertions.assertThat;
import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TermsOfServicePageTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    // terms_of_service.html - TESTS
    // Testing that links work
    @Test
    public void clickTermsOfServiceReturnLinkThenPageSourceContainsSignUp() {
        clickTermsOfServiceReturnLink();
    }

    public void clickTermsOfServiceReturnLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/TermsOfService");
        $("#backToRegisterPage").click();
        assertThat(pageSource()).contains("Sign up");
    }
}

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
public class LoginErrorPageTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    // login_error.html - TESTS
    // Testing that links work
    @Test
    public void clickLoginErrorReturnLinkThenPageSourceContainsUserLogin() {
        clickLoginErrorReturnLink();
    }

    public void clickLoginErrorReturnLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/LoginError");
        assertThat(pageSource()).contains("Somehow login was not completed!");
        assertThat(pageSource()).contains("Username or password is not correct.");
        $("#backToLoginPage").click();
        assertThat(pageSource()).contains("User Login");
    }
}

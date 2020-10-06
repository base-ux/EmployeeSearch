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
public class LoginPageTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    // login.html - TESTS
    // Testing that links and buttons work
    @Test
    public void clickLoginUserLoginButtonThenPageSourceContainsUserLogin() {
        clickLoginUserLoginButton();
    }

    public void clickLoginUserLoginButton() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#userLogin").click();
        assertThat(pageSource()).contains("User Login");
    }

    @Test
    public void clickLoginSignUpLinkThenPageSourceContainsSignUp() {
        clickLoginSignUpLink();
    }

    public void clickLoginSignUpLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#toSignupPage").click();
        assertThat(pageSource()).contains("Sign up");
    }

    @Test
    public void clickLoginBackToMainPageLinkThenPageSourceContainsPromotionText() {
        clickLoginBackToMainPageLink();
    }

    public void clickLoginBackToMainPageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#backToMainPage").click();
        assertThat(pageSource()).contains("are you waiting for? Register Today, it's completely");
    }
}

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
public class LoginPageTest extends FluentTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private Integer port;

    // AccountController - TESTS continued also in RegisterPageTest.java
    @Test
    public void getRequestToLoginErrorReturnsLoginErrorText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/LoginError")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Somehow login was not completed!")));
    }

    @Test
    public void getRequestToLoginReturnsLoginFillText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User Login")));
    }

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

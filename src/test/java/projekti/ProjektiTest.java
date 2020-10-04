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
public class ProjektiTest extends FluentTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private Integer port;

    // ACCOUNT_CONTROLLER-TESTS
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

    @Test
    public void getRequestToRegisterReturnsRegisterFillText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Register")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Allowed usual and special characters: capital and lowercase letters")));
    }

    @Test
    public void getRequestToRegisterSlashAliasReturnsRegisterOkText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Register/heippa")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("<p>Registering was successful for alias \n"
                        + "                    <span>heippa</span>.</p>")));
    }

    // DEFAULT_CONTROLLER-TESTS
    @Test
    public void getRequestToRootReturnsRedirect() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void getRequestToEmployeeSearchReturnsRedirect() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void getRequestToEmployeeSearchSlashReturnsRedirect() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/")).andDo(print()).andExpect(status().isFound());
    }

    // HOME-TESTS
    @Test
    public void home() {

    }

    // POSTS-TESTS
    @Test
    public void posts() {

    }

    // WELCOME_CONTROLLER-TESTS
    @Test
    public void getRequestToTermsOfServiceReturnsTermsOfServiceText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/TermsOfService")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("guarded-thicket-83287.herokuapp.com (the ")));
    }

    @Test
    public void getRequestToWelcomeReturnsWelcomeText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("As a Registered User you will get your own Home Page")));
    }

    // WELCOME_HTML-TESTS
    @Test
    public void clickWelcomeLoginButtonThenPageSourceContainsUserLogin() {
        clickWelcomeLoginButton();
    }

    public void clickWelcomeLoginButton() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("button[name='welcomeLoginButton']").click();
        assertThat(pageSource()).contains("User Login");
    }
    
    @Test
    public void clickWelcomeSignupButtonThenPageSourceContainsSignUp() {
        clickWelcomeSignupButton();
    }

    public void clickWelcomeSignupButton() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("button[name='welcomeSignupButton']").click();
        assertThat(pageSource()).contains("Sign up");
    }
    
    @Test
    public void clickWelcomeBigLogoThenPageSourceContainsPosts() {
        clickWelcomeBigLogo();
    }

    public void clickWelcomeBigLogo() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("img[name='welcomeBigLogo']").click();
        assertThat(pageSource()).contains("Posts");
    }
    
    @Test
    public void clickWelcomeTextButtonThenPageSourceContainsSignUp() {
        clickWelcomeTextButton();
    }

    public void clickWelcomeTextButton() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("button[name='welcomeTextButton']").click();
        assertThat(pageSource()).contains("Sign up");
    }
}

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
public class WelcomePageTest extends FluentTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private Integer port;

    // WELCOME_CONTROLLER-TESTS
    @Test
    public void getRequestToWelcomeReturnsWelcomeText() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("As a Registered User you will get your own Home Page")));
    }

    // WELCOME_HTML-TESTS
    @Test
    public void clickNavbarDropdownMenuLinkThenMyHomePageLinkThenPageSourceContainsUserLogin() {
        clickNavbarDropdownMenuLinkThenMyHomePageLink();
    }

    public void clickNavbarDropdownMenuLinkThenMyHomePageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("#navbarDropdownMenuLink").click();
        $("a[name='myHomePageLink']").click();
        assertThat(pageSource()).contains("User Login");
    }

    @Test
    public void clickNavbarDropdownMenuLinkThenLoginLinkThenPageSourceContainsUserLogin() {
        clickNavbarDropdownMenuLinkThenLoginLink();
    }

    public void clickNavbarDropdownMenuLinkThenLoginLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("#navbarDropdownMenuLink").click();
        $("a[name='loginLink']").click();
        assertThat(pageSource()).contains("User Login");
    }

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
    public void clickCarouselControlPrevThenDataSlideTo4IsActive() {
        clickCarouselControlPrev();
    }

    public void clickCarouselControlPrev() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("#carouselPrev").click();
        assertThat(pageSource()).contains("<li data-target=\"#carouselIndicators\" data-slide-to=\"4\" class=\"active\"/>");
    }

    @Test
    public void clickCarouselControlNextThenDataSlideTo1IsActive() {
        clickCarouselControlNext();
    }

    public void clickCarouselControlNext() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Welcome");
        $("#carouselNext").click();
        assertThat(pageSource()).contains("<li data-target=\"#carouselIndicators\" data-slide-to=\"1\" class=\"active\"/>");
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

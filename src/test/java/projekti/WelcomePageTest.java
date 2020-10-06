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
public class WelcomePageTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    // Not logged in
    // welcome.html - TESTS
    // Testing that links and buttons work
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

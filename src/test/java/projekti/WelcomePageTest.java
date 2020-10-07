package projekti;

import org.apache.commons.lang3.RandomStringUtils;
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

    // welcome.html - TESTS
    // Testing that links and buttons work
    // Not logged in
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

    // Testing that links and buttons work
    // Logged in
    @Test
    public void afterLoginInWelcomePageClickNavbarDropdownMenuLinkThenMyHomePageLinkThenPageSourceContainsHomeText() {
        loginInWelcomePageClickNavbarDropdownMenuLinkThenMyHomePageLink();
    }

    public void loginInWelcomePageClickNavbarDropdownMenuLinkThenMyHomePageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "AA_@" + RandomStringUtils.randomAlphanumeric(56);
        $("input[name=username]").fill().with(userName);
        String passWord = "bR8@" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver" + RandomStringUtils.randomAlphabetic(18);
        $("input[name=realname]").fill().with(realName);
        String aliAs = "toni" + RandomStringUtils.randomAlphabetic(26);
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#LoginFormUsername").fill().with(userName);
        $("#LoginFormPassword").fill().with(passWord);
        $("#userLogin").click();
        assertThat(pageSource()).contains("Hello, " + aliAs + "!");
        assertThat(pageSource()).contains(aliAs + " (Logged in)");
        assertThat(pageSource()).contains("Home");
        assertThat(pageSource()).contains("Posts");
        $("#navbarDropdownMenuLink").click();
        $("a[name='myHomePageLink']").click();
        assertThat(pageSource()).contains("This is my Homepage");
    }

    @Test
    public void afterLoginInWelcomePageClickNavbarDropdownMenuLinkThenLogoutLinkThenPageSourceContainsPromotionText() {
        loginInWelcomePageClickNavbarDropdownMenuLinkThenLogoutLink();
    }

    public void loginInWelcomePageClickNavbarDropdownMenuLinkThenLogoutLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "AA_@" + RandomStringUtils.randomAlphanumeric(56);
        $("input[name=username]").fill().with(userName);
        String passWord = "bR8@" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver" + RandomStringUtils.randomAlphabetic(18);
        $("input[name=realname]").fill().with(realName);
        String aliAs = "toni" + RandomStringUtils.randomAlphabetic(26);
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#LoginFormUsername").fill().with(userName);
        $("#LoginFormPassword").fill().with(passWord);
        $("#userLogin").click();
        assertThat(pageSource()).contains("Hello, " + aliAs + "!");
        assertThat(pageSource()).contains(aliAs + " (Logged in)");
        assertThat(pageSource()).contains("Home");
        assertThat(pageSource()).contains("Posts");
        $("#navbarDropdownMenuLink").click();
        $("a[name='logoutLink']").click();
        assertThat(pageSource()).contains("that you can modify to your liking and show others");
    }

    @Test
    public void afterLoginInWelcomePageClickLogoutButtonThenPageSourceContainsPromotionText() {
        loginInWelcomePageClickLogoutButton();
    }

    public void loginInWelcomePageClickLogoutButton() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "AA_@" + RandomStringUtils.randomAlphanumeric(56);
        $("input[name=username]").fill().with(userName);
        String passWord = "bR8@" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver" + RandomStringUtils.randomAlphabetic(18);
        $("input[name=realname]").fill().with(realName);
        String aliAs = "toni" + RandomStringUtils.randomAlphabetic(26);
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#LoginFormUsername").fill().with(userName);
        $("#LoginFormPassword").fill().with(passWord);
        $("#userLogin").click();
        assertThat(pageSource()).contains("Hello, " + aliAs + "!");
        assertThat(pageSource()).contains(aliAs + " (Logged in)");
        assertThat(pageSource()).contains("Home");
        assertThat(pageSource()).contains("Posts");
        $("button[name='welcomeLogoutButton']").click();
        assertThat(pageSource()).contains("that you can modify to your liking and show others");
    }

    @Test
    public void afterLoginInWelcomePageClickHomeLinkThenPageSourceContainsHomeText() {
        loginInWelcomePageClickHomeLink();
    }

    public void loginInWelcomePageClickHomeLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "AA_@" + RandomStringUtils.randomAlphanumeric(56);
        $("input[name=username]").fill().with(userName);
        String passWord = "bR8@" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver" + RandomStringUtils.randomAlphabetic(18);
        $("input[name=realname]").fill().with(realName);
        String aliAs = "toni" + RandomStringUtils.randomAlphabetic(26);
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#LoginFormUsername").fill().with(userName);
        $("#LoginFormPassword").fill().with(passWord);
        $("#userLogin").click();
        assertThat(pageSource()).contains("Hello, " + aliAs + "!");
        assertThat(pageSource()).contains(aliAs + " (Logged in)");
        assertThat(pageSource()).contains("Home");
        assertThat(pageSource()).contains("Posts");
        $("a[name='welcomeHomeLink']").click();
        assertThat(pageSource()).contains("This is my Homepage");
    }

    @Test
    public void afterLoginInWelcomePageClickPostsLinkThenPageSourceContainsPostsText() {
        loginInWelcomePageClickPostsLink();
    }

    public void loginInWelcomePageClickPostsLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "AA_@" + RandomStringUtils.randomAlphanumeric(56);
        $("input[name=username]").fill().with(userName);
        String passWord = "bR8@" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver" + RandomStringUtils.randomAlphabetic(18);
        $("input[name=realname]").fill().with(realName);
        String aliAs = "toni" + RandomStringUtils.randomAlphabetic(26);
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#LoginFormUsername").fill().with(userName);
        $("#LoginFormPassword").fill().with(passWord);
        $("#userLogin").click();
        assertThat(pageSource()).contains("Hello, " + aliAs + "!");
        assertThat(pageSource()).contains(aliAs + " (Logged in)");
        assertThat(pageSource()).contains("Home");
        assertThat(pageSource()).contains("Posts");
        $("a[name='welcomePostsLink']").click();
        assertThat(pageSource()).contains("Here are Posts");
    }
}

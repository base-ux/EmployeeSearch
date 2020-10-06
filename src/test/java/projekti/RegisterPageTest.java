package projekti;

import org.apache.commons.lang3.RandomStringUtils;
import static org.assertj.core.api.Assertions.assertThat;
import org.fluentlenium.adapter.junit.FluentTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterPageTest extends FluentTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @LocalServerPort
    private Integer port;

    // register.html - TESTS
    // Testing that links and buttons work
    @Test
    public void clickRegisterSignupButtonThenPageSourceContainsSignUp() {
        clickRegisterSignupButton();
    }

    public void clickRegisterSignupButton() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        $("#signup").click();
        assertThat(pageSource()).contains("Sign up");
    }

    @Test
    public void clickToTermsOfServicePageLinkThenPageSourceContainsTermsOfServiceText() {
        clickToTermsOfServicePageLink();
    }

    public void clickToTermsOfServicePageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        $("#toTermsOfServicePage").click();
        assertThat(pageSource()).contains("or the ability of any other person to access");
    }

    @Test
    public void clickRegisterBackToMainPageLinkThenPageSourceContainsPromotionText() {
        clickRegisterBackToMainPageLink();
    }

    public void clickRegisterBackToMainPageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        $("#backToMainPage").click();
        assertThat(pageSource()).contains("documents, profile avatar and more");
    }

    // Testing that sign up form works
    @Test
    public void canAddAccountWithShortInputs() {
        addAccountWithShortInputs();
    }

    public void addAccountWithShortInputs() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE";
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(4);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "TS";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "to";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("Registering was successful for alias");
        assertThat(pageSource()).contains("to");
        assertEquals(1, jdbcTemplate.queryForList("SELECT * FROM Account")
                .stream().filter(s -> s.values().stream().filter(t -> t.equals(userName))
                .count() > 0).count());
    }

    @Test
    public void canAddAccountWithLongInputs() {
        addAccountWithLongInputs();
    }

    public void addAccountWithLongInputs() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver 1234567";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa 12345678";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("Registering was successful for alias");
        assertThat(pageSource()).contains("tontsa12345678");
        assertEquals(1, jdbcTemplate.queryForList("SELECT * FROM Account")
                .stream().filter(s -> s.values().stream().filter(t -> t.equals(userName))
                .count() > 0).count());
    }

    @Test
    public void fieldsSignUpCannotBeEmptyFail() {
        signUpCannotBeEmptyFail();
    }

    public void signUpCannotBeEmptyFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        $("#signup").click();
        assertThat(pageSource()).contains("must not be empty");
        assertThat(pageSource()).contains("size must be between 2 and 25");
        assertThat(pageSource()).contains("size must be between 8 and 60");
        assertThat(pageSource()).contains("size must be between 2 and 20");
        assertThat(pageSource()).contains("size must be between 2 and 15");
    }

    @Test
    public void tooShortUsernameFail() {
        shortUsernameFail();
    }

    public void shortUsernameFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "E";
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 2 and 25");
    }

    @Test
    public void tooLongUsernameFail() {
        longUsernameFail();
    }

    public void longUsernameFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@1234567890123456789012";
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 2 and 25");
    }

    @Test
    public void fieldUsernameCannotBeEmptyFail() {
        usernameCannotBeEmptyFail();
    }

    public void usernameCannotBeEmptyFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("must not be empty");
        assertThat(pageSource()).contains("size must be between 2 and 25");
    }

    @Test
    public void tooShortPasswordFail() {
        shortPasswordFail();
    }

    public void shortPasswordFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(3);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 8 and 60");
        assertThat(pageSource()).contains("Password must be 8 or more characters in length.");
    }

    @Test
    public void tooLongPasswordFail() {
        longPasswordFail();
    }

    public void longPasswordFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(57);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 8 and 60");
        assertThat(pageSource()).contains("Password must be no more than 60 characters in length.");
    }

    @Test
    public void fieldPasswordCannotBeEmptyFail() {
        passwordCannotBeEmptyFail();
    }

    public void passwordCannotBeEmptyFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        assertThat(pageSource()).contains("confirm must match password");
    }

    @Test
    public void tooShortConfirmFail() {
        shortConfirmFail();
    }

    public void shortConfirmFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(4);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm.substring(1));
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        assertThat(pageSource()).contains("confirm must match password");
    }

    @Test
    public void tooLongConfirmFail() {
        longConfirmFail();
    }

    public void longConfirmFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(4);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm + "0");
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        assertThat(pageSource()).contains("confirm must match password");
    }

    @Test
    public void fieldConfirmCannotBeEmptyFail() {
        confirmCannotBeEmptyFail();
    }

    public void confirmCannotBeEmptyFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(4);
        $("input[name=password]").fill().with(passWord);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        assertThat(pageSource()).contains("confirm must match password");
    }

    @Test
    public void tooShortRealnameFail() {
        shortRealnameFail();
    }

    public void shortRealnameFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "T";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 2 and 20");
    }

    @Test
    public void tooLongRealnameFail() {
        longRealnameFail();
    }

    public void longRealnameFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver 12345678";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 2 and 20");
    }

    @Test
    public void fieldRealnameCannotBeEmptyFail() {
        realnameCannotBeEmptyFail();
    }

    public void realnameCannotBeEmptyFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String aliAs = "tontsa";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("must not be empty");
        assertThat(pageSource()).contains("size must be between 2 and 20");
    }

    @Test
    public void tooShortAliasFail() {
        shortAliasFail();
    }

    public void shortAliasFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "t";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 2 and 15");
    }

    @Test
    public void tooLongAliasFail() {
        longAliasFail();
    }

    public void longAliasFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa 123456789";
        $("input[name=alias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("size must be between 2 and 15");
    }

    @Test
    public void fieldAliasCannotBeEmptyFail() {
        aliasCannotBeEmptyFail();
    }

    public void aliasCannotBeEmptyFail() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "EE_@" + RandomStringUtils.randomAlphanumeric(21);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        $("#signup").click();
        assertThat(pageSource()).contains("must not be empty");
        assertThat(pageSource()).contains("size must be between 2 and 15");
    }
}

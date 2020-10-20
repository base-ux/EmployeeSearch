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
public class RegisterErrorPageTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    // register_error.html - TESTS
    // Testing that links work
    @Test
    public void clickRegisterErrorBackToRegisterPageLinkThenPageSourceContainsSignUp() {
        clickRegisterErrorBackToRegisterPageLink();
    }

    public void clickRegisterErrorBackToRegisterPageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = RandomStringUtils.randomAscii(20) + RandomStringUtils.randomGraph(20) + RandomStringUtils.randomPrint(20);
        $("input[name=username]").fill().with(userName);
        String passWord = "aZ0&" + RandomStringUtils.randomAlphabetic(56);
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver" + RandomStringUtils.randomAlphabetic(18);
        $("input[name=realname]").fill().with(realName);
        String aliAs = "tontsa" + RandomStringUtils.randomAlphabetic(24);
        $("input[name=useralias]").fill().with(aliAs);
        $("#signup").click();
        assertThat(pageSource()).contains("Somehow registering for user");
        assertThat(pageSource()).contains("Some of the entered characters were");
        $("#backToRegisterPage").click();
        assertThat(pageSource()).contains("Sign up");
    }
}

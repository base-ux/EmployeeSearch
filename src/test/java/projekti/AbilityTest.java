package projekti;

import static org.assertj.core.api.Assertions.assertThat;
import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.Before;
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
public class AbilityTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    @Before
    public void setUp() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register");
        String userName = "toni";
        $("input[name=username]").fill().with(userName);
        String passWord = "nY&2gfaskd6785vchkLKHGUJ&_@IBTI8ut_IV&Ri7@rii";
        $("input[name=password]").fill().with(passWord);
        String conFirm = passWord;
        $("input[name=confirm]").fill().with(conFirm);
        String realName = "Toni Silfver";
        $("input[name=realname]").fill().with(realName);
        String useralias = "tontsa";
        $("input[name=useralias]").fill().with(useralias);
        $("#signup").click();
        goTo("http://localhost:" + port + "/EmployeeSearch/Login");
        $("#LoginFormUsername").fill().with(userName);
        $("#LoginFormPassword").fill().with(passWord);
        $("#userLogin").click();
    }

    // Logged in
    // layout_home_nonedit - TESTS
    // Testing that ability adding works
    @Test
    public void clickAddAbilityLinkAndFillTextAndClickAddButton() {
        $("#addAbilityLink").click();
        find("#addAbilityTextBox").fill().with("Lisätään taito 1.");
        $("#addAbilityButton").click();
        assertThat(pageSource()).contains("My Home Page");
        assertThat(pageSource()).contains("Lisätään taito 1.");
        assertThat(pageSource()).contains("Praises:");
    }
}

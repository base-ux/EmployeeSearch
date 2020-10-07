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
public class RegisterOkPageTest extends FluentTest {

    @LocalServerPort
    private Integer port;

    // register_ok.html - TESTS
    // Testing that links work
    @Test
    public void clickRegisterOkBackToMainPageLinkThenPageSourceContainsPromotionText() {
        clickRegisterOkBackToMainPageLink();
    }

    public void clickRegisterOkBackToMainPageLink() {
        goTo("http://localhost:" + port + "/EmployeeSearch/Register/moi");
        assertThat(pageSource()).contains("Registering was successful for alias");
        assertThat(pageSource()).contains("moi");
        $("#backToMainPage").click();
        assertThat(pageSource()).contains("You can even add other employees to your contacts and");
    }
}

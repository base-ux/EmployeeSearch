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

    // welcome.html - TESTS
    // Testing that links and buttons work
    // Not logged in
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
}

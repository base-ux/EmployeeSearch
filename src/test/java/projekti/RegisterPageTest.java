package projekti;

import org.fluentlenium.adapter.junit.FluentTest;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class RegisterPageTest extends FluentTest {

    @Autowired
    private MockMvc mockMvc;

    // ACCOUNT_CONTROLLER-TESTS
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
}

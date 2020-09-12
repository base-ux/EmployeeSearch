package projekti;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjektiTest {

    @Autowired
    private MockMvc mockMvc;

    // WELCOME-TESTS
    @Test
    public void getRequestToRootReturnsRedirect() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void getRequestToWelcomeReturnsWelcomePhrase() throws Exception {
        this.mockMvc.perform(get("/EmployeeSearch/Welcome")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to Employee Search App")))
                .andExpect(content().string(not(containsString("gaslo4tghj38g92"))));
    }

    // POSTS-TESTS
    @Test
    public void posts() {
        
    }

    // HOME-TESTS
    @Test
    public void home() {
        
    }
}

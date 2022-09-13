package cz.palivtom.httpmonitoring.config.security;

import cz.palivtom.httpmonitoring.model.User;
import cz.palivtom.httpmonitoring.repository.UserRepository;
import cz.palivtom.httpmonitoring.utils.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static cz.palivtom.httpmonitoring.TestController.TESTING_ACCESS_TOKEN_PATH;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class AccessTokenFilterTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    private User user = Generator.generateUser();

    @BeforeEach
    public void setUp() {
        user = userRepository.save(user);
    }

    @Test
    void secured_unauthorizedRequest_failedToken() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(TESTING_ACCESS_TOKEN_PATH)
                        .header("accessToken", "doest-not-match-any")
                )
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void secured_forbiddenRequest_anyFilterMatch() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(TESTING_ACCESS_TOKEN_PATH))
                .andExpect(status().isForbidden());
    }

    @Test
    void secured_okRequest_authorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(TESTING_ACCESS_TOKEN_PATH)
                        .header("accessToken", user.getAccessToken())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string(user.getEmail()));
    }
}
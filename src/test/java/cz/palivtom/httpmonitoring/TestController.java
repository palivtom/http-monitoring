package cz.palivtom.httpmonitoring;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public static final String TESTING_ACCESS_TOKEN_PATH = "/test-access-token-filter";

    @GetMapping(TESTING_ACCESS_TOKEN_PATH)
    public String testEndpoint(@AuthenticationPrincipal String activeUserEmail) {
        return activeUserEmail;
    }
}
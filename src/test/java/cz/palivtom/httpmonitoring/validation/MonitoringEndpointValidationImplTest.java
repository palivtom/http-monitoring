package cz.palivtom.httpmonitoring.validation;

import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.model.User;
import cz.palivtom.httpmonitoring.repository.MonitoringEndpointRepository;
import cz.palivtom.httpmonitoring.utils.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest(properties = { "spring.host=localhost" })
@ActiveProfiles(profiles = "test")
class MonitoringEndpointValidationImplTest {

    @Autowired
    private MonitoringEndpointValidationImpl validator;

    @MockBean
    private MonitoringEndpointRepository monitoringEndpointRepository;

    private static User user = Generator.generateUser();

    private MonitoringEndpoint existingMonitoringEndpoint;


    @BeforeEach
    private void setUp() {
        existingMonitoringEndpoint = Generator.generateMonitoringEndpoint();
        existingMonitoringEndpoint.setUser(user);
    }

    @Test
    void onCreate_emptyTitle_exception() {
        var toTest = Generator.generateMonitoringEndpoint();
        toTest.setTitle("");

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onCreate(toTest));
    }

    @Test
    void onCreate_tooLongTitle_exception() {
        var toTest = Generator.generateMonitoringEndpoint();
        toTest.setTitle(" ".repeat(256));

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onCreate(toTest));
    }

    @Test
    void onCreate_okTitle_noExceptionThrown() {
        var toTest = Generator.generateMonitoringEndpoint();

        Assertions.assertDoesNotThrow(() -> validator.onCreate(toTest));
    }

    @Test
    void onCreate_negativeInterval_exception() {
        var toTest = Generator.generateMonitoringEndpoint();
        toTest.setInterval(-100);

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onCreate(toTest));
    }

    @Test
    void onCreate_zeroInterval_exception() {
        var toTest = Generator.generateMonitoringEndpoint();
        toTest.setInterval(0);

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onCreate(toTest));
    }

    @Test
    void onCreate_okInterval_noExceptionThrown() {
        var toTest = Generator.generateMonitoringEndpoint();

        Assertions.assertDoesNotThrow(() -> validator.onCreate(toTest));
    }

    private static MonitoringEndpoint urlTests_generateMonitoringEndpoint(String url) {
        var toTest = Generator.generateMonitoringEndpoint();
        toTest.setUrl(url);
        return toTest;
    }

    private static Stream<Arguments> urlTests_provider() {
        return Stream.of(
                Arguments.of(urlTests_generateMonitoringEndpoint("")),
                Arguments.of(urlTests_generateMonitoringEndpoint(" ".repeat(2048))),
                Arguments.of(urlTests_generateMonitoringEndpoint("http://localhost:8080")),
                Arguments.of(urlTests_generateMonitoringEndpoint("ftp://google.com")),
                Arguments.of(urlTests_generateMonitoringEndpoint("google.com")),
                Arguments.of(urlTests_generateMonitoringEndpoint("http://google")),
                Arguments.of(urlTests_generateMonitoringEndpoint("https://google")),
                Arguments.of(urlTests_generateMonitoringEndpoint("http://"))
        );
    }

    @ParameterizedTest
    @MethodSource("urlTests_provider")
    void onCreate_invalidUrls_exception(MonitoringEndpoint toTest) {
        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onCreate(toTest));
    }

    @Test
    void onCreate_unableResolveHostUrl_exception() {
        var toTest = Generator.generateMonitoringEndpoint();
        toTest.setUrl("https://definitely-not-exist-lllllllllll.com");

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onCreate(toTest));
    }

    @Test
    void onGet_endpointExists_noExceptionThrown() {
        Mockito.when(monitoringEndpointRepository.findById(anyLong()))
                .then(answer -> Optional.of(existingMonitoringEndpoint));

        Assertions.assertDoesNotThrow(() -> validator.onGet(666L));
    }

    @Test
    void onGet_endpointDoesNotExist_exception() {
        Mockito.when(monitoringEndpointRepository.findById(anyLong()))
                .then(answer -> Optional.empty());

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onGet(666L));
    }

    @Test
    void onUpdate_theEndpointDoesNotExits_exception() {
        var toTest = Generator.generateMonitoringEndpoint();

        Mockito.when(monitoringEndpointRepository.findById(anyLong()))
                .then(answer -> Optional.empty());

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onUpdate(toTest, 666L));
    }

    @Test
    void onUpdate_theUserIsAnAuthorOfEndpoint_noExceptionThrown() {
        var toTest = Generator.generateMonitoringEndpoint();
        Mockito.when(monitoringEndpointRepository.findById(anyLong()))
                .then(answer -> Optional.of(existingMonitoringEndpoint));

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getAccessToken(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Assertions.assertDoesNotThrow(() -> validator.onUpdate(toTest, 666L));
    }

    @Test
    void onUpdate_theUserIsNotAnAuthorOfEndpoint_exception() {
        var toTest = Generator.generateMonitoringEndpoint();
        Mockito.when(monitoringEndpointRepository.findById(anyLong()))
                .then(answer -> Optional.of(existingMonitoringEndpoint));

        User newRandomUser = Generator.generateUser();
        Authentication auth = new UsernamePasswordAuthenticationToken(newRandomUser.getEmail(), newRandomUser.getAccessToken(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Assertions.assertThrows(ApiRuntimeRuntimeException.class, () -> validator.onUpdate(toTest, 666L));
    }
}
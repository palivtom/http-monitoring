package cz.palivtom.httpmonitoring.validation;

import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.repository.MonitoringEndpointRepository;
import cz.palivtom.httpmonitoring.service.interfaces.UserService;
import cz.palivtom.httpmonitoring.validation.interfaces.MonitoringEndpointValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MonitoringEndpointValidationImpl implements MonitoringEndpointValidation {

    private static final int TITLE_MAX_LENGTH = 255;
    private static final int URL_MAX_LENGTH = 2048;

    @Value("${server.host:localhost}")
    private String serverUrl;

    private final MonitoringEndpointRepository monitoringEndpointRepository;
    private final UserService userService;

    @Override
    public void onCreate(MonitoringEndpoint toCreate) {
        validateBody(toCreate);
    }

    @Override
    public void onGet(Long id) {
        exists(id);
    }

    @Override
    public void onUpdate(MonitoringEndpoint toUpdate, Long id) {
        validateBody(toUpdate);
        MonitoringEndpoint endpoint = exists(id);
        isAuthor(endpoint);
    }

    @Override
    public void onRemove(Long id) {
        MonitoringEndpoint endpoint = exists(id);
        isAuthor(endpoint);
    }

    private void validateBody(MonitoringEndpoint monitoringEndpoint) {
        validateInterval(monitoringEndpoint.getInterval());
        validateTitle(monitoringEndpoint.getTitle());
        validateUrl(monitoringEndpoint.getUrl());
    }

    private MonitoringEndpoint exists(Long id) {
        Optional<MonitoringEndpoint> endpoint = monitoringEndpointRepository.findById(id);
        if (endpoint.isEmpty()) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Monitoring endpoint id = '{}' does not exist.");
        }
        return endpoint.get();
    }

    private void isAuthor(MonitoringEndpoint monitoringEndpoint) {
        if (!userService.getEmail().equals(monitoringEndpoint.getUser().getEmail())) {
            throw new ApiRuntimeRuntimeException(HttpStatus.FORBIDDEN, "Only author can manipulate with monitoring endpoint id = '{}'.", monitoringEndpoint.getId());
        }
    }

    private void validateInterval(Integer interval) {
        if (interval <= 0) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Interval has to be a positive number.");
        }
    }

    private void validateTitle(String title) {
        if (title.isEmpty()) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Title is required.");
        } else if (title.length() > TITLE_MAX_LENGTH) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Title can be max '{}' char long.", TITLE_MAX_LENGTH);
        }
    }

    private void validateUrl(String url) {
        if (url.isEmpty()) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Url is required.");
        } else if (url.length() > URL_MAX_LENGTH) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Url can be max '{}' char long.", URL_MAX_LENGTH);
        }

        try {
            if (new URL(url).getHost().equals(serverUrl)) {
                throw new ApiRuntimeRuntimeException(HttpStatus.METHOD_NOT_ALLOWED, "Given URL is pointing to service itself.");
            }
        } catch (MalformedURLException e) {
            throw new ApiRuntimeRuntimeException(HttpStatus.BAD_REQUEST, "Invalid URL format.");
        }
    }
}
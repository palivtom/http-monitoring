package cz.palivtom.httpmonitoring.service;

import cz.palivtom.httpmonitoring.exception.MonitoringEndpointAlreadyRegisteredException;
import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.MonitoringEndpoint;
import cz.palivtom.httpmonitoring.repository.MonitoringEndpointRepository;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointResponseService;
import cz.palivtom.httpmonitoring.service.interfaces.MonitoringEndpointService;
import cz.palivtom.httpmonitoring.service.interfaces.UserService;
import cz.palivtom.httpmonitoring.utils.MonitoringEndpointRestCall;
import cz.palivtom.httpmonitoring.validation.interfaces.MonitoringEndpointValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
@Log4j2
@Transactional
@EnableScheduling
@Service
public class MonitoringEndpointServiceImpl implements MonitoringEndpointService {

    private final MonitoringEndpointRepository monitoringEndpointRepository;
    private final MonitoringEndpointValidation monitoringEndpointValidation;
    private final UserService userService;
    private final MonitoringEndpointResponseService monitoringEndpointResponseService;
    private final TaskScheduler taskScheduler;

    private final Map<Long, ScheduledFuture<?>> scheduledFutureSet = new HashMap<>();

    @PostConstruct
    void init() throws MonitoringEndpointAlreadyRegisteredException {
        for (MonitoringEndpoint monitoringEndpoint : monitoringEndpointRepository.findAll()) {
            if (monitoringEndpoint.getDeletedAt() == null) {
                register(monitoringEndpoint);
            }
        }
    }

    @PreDestroy
    void destroy() {
        scheduledFutureSet.forEach((key, value) -> value.cancel(true));
    }

    @Override
    public MonitoringEndpoint create(MonitoringEndpoint toCreate) {
        monitoringEndpointValidation.onCreate(toCreate);

        toCreate.setUser(userService.getUser());
        MonitoringEndpoint monitoringEndpoint = monitoringEndpointRepository.save(toCreate);

        try {
            register(monitoringEndpoint);
        } catch (MonitoringEndpointAlreadyRegisteredException e) {
            throwUnableRegisterException(monitoringEndpoint);
        }

        return monitoringEndpoint;
    }

    @Override
    public Page<MonitoringEndpoint> getAll(Pageable pageable, boolean validOnly) {
        if (validOnly) {
            return monitoringEndpointRepository.findAllByUser_EmailAndDeletedAt(userService.getEmail(), null, pageable);
        } else {
            return monitoringEndpointRepository.findAllByUser_Email(userService.getEmail(), pageable);
        }
    }

    @Override
    public MonitoringEndpoint get(Long id) {
        monitoringEndpointValidation.onGet(id);
        return monitoringEndpointRepository.findById(id).orElseThrow();
    }

    @Override
    public MonitoringEndpoint update(MonitoringEndpoint toUpdate, Long id) {
        monitoringEndpointValidation.onUpdate(toUpdate, id);

        MonitoringEndpoint monitoringEndpoint = monitoringEndpointRepository.findById(id).orElseThrow();
        unregister(monitoringEndpoint);

        monitoringEndpoint.setTitle(toUpdate.getTitle());
        monitoringEndpoint.setUrl(toUpdate.getUrl());
        monitoringEndpoint.setInterval(toUpdate.getInterval());

        monitoringEndpoint = monitoringEndpointRepository.save(monitoringEndpoint);

        try {
            register(monitoringEndpoint);
        } catch (MonitoringEndpointAlreadyRegisteredException e) {
            throwUnableRegisterException(monitoringEndpoint);
        }

        return monitoringEndpoint;
    }

    @Override
    public MonitoringEndpoint delete(Long id) {
        monitoringEndpointValidation.onRemove(id);

        MonitoringEndpoint monitoringEndpoint = monitoringEndpointRepository.findById(id).orElseThrow();
        unregister(monitoringEndpoint);

        monitoringEndpoint.setDeletedAt(LocalDateTime.now());

        return monitoringEndpointRepository.save(monitoringEndpoint);
    }

    private void register(MonitoringEndpoint monitoringEndpoint) throws MonitoringEndpointAlreadyRegisteredException {
        if (scheduledFutureSet.containsKey(monitoringEndpoint.getId())) {
            throw new MonitoringEndpointAlreadyRegisteredException(monitoringEndpoint);
        }

        scheduledFutureSet.put(monitoringEndpoint.getId(), taskScheduler.scheduleAtFixedRate(
                new MonitoringEndpointRestCall(monitoringEndpointResponseService, monitoringEndpoint), monitoringEndpoint.getInterval() * 1000L)
        );
        log.info("Monitoring endpoint [id = '{}', interval = '{}', url = '{}'] has been registered.", monitoringEndpoint.getCreatedAt(), monitoringEndpoint.getInterval(), monitoringEndpoint.getUrl());
    }

    private void unregister(MonitoringEndpoint monitoringEndpoint) {
        scheduledFutureSet.remove(monitoringEndpoint.getId()).cancel(true);
        log.info("Monitoring endpoint [id = '{}', interval = '{}', url = '{}'] has been unregistered.", monitoringEndpoint.getCreatedAt(), monitoringEndpoint.getInterval(), monitoringEndpoint.getUrl());
    }

    private void throwUnableRegisterException(MonitoringEndpoint monitoringEndpoint) {
        ApiRuntimeRuntimeException ex = new ApiRuntimeRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot register given monitoring endpoint id = '{}'", monitoringEndpoint.getId());
        log.error(ex.getMessage());
        throw ex;
    }
}
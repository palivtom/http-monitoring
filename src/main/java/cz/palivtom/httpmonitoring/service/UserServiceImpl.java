package cz.palivtom.httpmonitoring.service;

import cz.palivtom.httpmonitoring.exception.runtime.ApiRuntimeRuntimeException;
import cz.palivtom.httpmonitoring.model.User;
import cz.palivtom.httpmonitoring.repository.UserRepository;
import cz.palivtom.httpmonitoring.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String getEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getUser() {
        return userRepository.findByEmail(getEmail())
                .orElseThrow(() -> new ApiRuntimeRuntimeException(HttpStatus.METHOD_NOT_ALLOWED, "Security context is empty."));
    }
}
package cz.palivtom.httpmonitoring.service;

import cz.palivtom.httpmonitoring.repository.UserRepository;
import cz.palivtom.httpmonitoring.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //todo
}
package cz.palivtom.httpmonitoring.service.interfaces;

import cz.palivtom.httpmonitoring.model.User;
import org.springframework.security.core.context.SecurityContext;

public interface UserService {

    /**
     * @return the current principal's email in {@link SecurityContext}
     */
    String getEmail();

    /**
     * @return the current {@link User} in {@link SecurityContext}
     */
    User getUser();
}
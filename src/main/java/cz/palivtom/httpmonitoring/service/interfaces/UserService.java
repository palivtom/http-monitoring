package cz.palivtom.httpmonitoring.service.interfaces;

import cz.palivtom.httpmonitoring.model.User;

public interface UserService {
    String getEmail();
    User getUser();
}
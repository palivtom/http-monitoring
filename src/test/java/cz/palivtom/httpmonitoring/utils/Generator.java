package cz.palivtom.httpmonitoring.utils;

import cz.palivtom.httpmonitoring.model.User;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class Generator {
    public User generateUser() {
        String accessToken = UUID.randomUUID().toString();
        User user = new User();
        user.setAccessToken(accessToken);
        user.setUsername("username_" + accessToken);
        user.setEmail(accessToken + "@httpmonitoring.com");
        return user;
    }
}

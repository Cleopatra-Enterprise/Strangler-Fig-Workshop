package com.ces.slc.workshop.security.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ces.slc.workshop.security.domain.User;

@Service
public class ServiceAccountObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAccountObserver.class);

    @Value("${cleopatra.admin.username}")
    private String username;

    @Value("${cleopatra.admin.password}")
    private String password;

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ServiceAccountObserver(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted() {
        userRepository.getUserByUsername(username)
                        .ifPresentOrElse(
                                this::updateServiceAccountIfChanged,
                                this::createServiceAccount
                        );
    }

    private void createServiceAccount() {
        userRepository.save(new User(username, password));
        LOGGER.info("Service account ({}) created", username);
    }

    private void updateServiceAccountIfChanged(User user) {
        if (!encoder.matches(user.getPassword(), password)) {
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
            LOGGER.info("Service account ({}) updated", username);
        }
        else {
            LOGGER.info("Service account ({}) unchanged", username);
        }
    }
}

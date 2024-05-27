package com.ces.slc.workshop.security.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAccountObserver.class);

    @Value("${cleopatra.admin.username}")
    private String username;

    @Value("${cleopatra.admin.password}")
    private String password;

    private final CustomUserDetailsService userDetailsManager;

    public ServiceAccountObserver(CustomUserDetailsService userDetailsService) {
        this.userDetailsManager = userDetailsService;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted() {
        if (userDetailsManager.userExists(username)) {
            UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
            boolean updated = userDetailsManager.updatePasswordIfChanged(userDetails, password);
            if (updated) {
                LOGGER.info("Service account password updated");
            }
        }
        else {
            createServiceAccount();
        }
    }

    private void createServiceAccount() {
        UserDetails details = User.withUsername(username)
                .password(password)
                .build();
        userDetailsManager.createUser(details);
        LOGGER.info("Service account created with username: {}", username);
    }
}

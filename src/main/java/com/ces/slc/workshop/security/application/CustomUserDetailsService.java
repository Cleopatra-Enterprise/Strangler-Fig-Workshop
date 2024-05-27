package com.ces.slc.workshop.security.application;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import com.ces.slc.workshop.security.domain.CustomUserDetails;
import com.ces.slc.workshop.security.domain.User;

@Component
public class CustomUserDetailsService implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void createUser(UserDetails user) {
        String encodedPass = encoder.encode(user.getPassword());
        userRepository.save(new User(user.getUsername(), encodedPass));
    }

    @Override
    public void updateUser(UserDetails user) {
        updatePasswordIfChanged(user, user.getPassword());
    }

    public boolean updatePasswordIfChanged(UserDetails user, String newPassword) {
        User existingUser = user instanceof CustomUserDetails customUserDetails
                ? customUserDetails.user()
                : userRepository.findById(user.getUsername()).orElse(null);

        if(existingUser == null) {
            return false;
        }

        if(encoder.matches(newPassword, existingUser.getPassword())) {
            return false;
        }

        String encodedPass = encoder.encode(newPassword);
        existingUser.setPassword(encodedPass);
        userRepository.save(existingUser);
        return true;
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // Not implemented
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }
}

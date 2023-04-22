package com.app.commerce.data;

import com.app.commerce.entity.Role;
import com.app.commerce.entity.User;
import com.app.commerce.repository.RoleRepository;
import com.app.commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(value = 2)
public class UserDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        Role adminRole = roleRepository
                .findByCode("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("There is no admin role"));

        User admin = new User()
                .setUsername("admin")
                .setEmail("admin@gmail.com")
                .setRoles(List.of(adminRole))
                .setFullName("admin")
                .setPassword(passwordEncoder.encode("Aqswde123@")); // Aqswde123@
        userRepository.save(admin);
    }
}

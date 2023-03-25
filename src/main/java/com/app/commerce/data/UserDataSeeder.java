package com.app.commerce.data;

import com.app.commerce.entity.User;
import com.app.commerce.enums.Role;
import com.app.commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        User admin = new User()
                .setEmail("admin@gmail.com")
                .setRole(Role.ADMIN)
                .setFirstName("admin")
                .setLastName("admin")
                .setPassword("$2a$12$2VFbg2IFtFOrc/xuDsjZoOjyA6vn6exuzT4vTae8TtV4bdIszDTPW"); // Aqswde123@
        userRepository.save(admin);
    }
}

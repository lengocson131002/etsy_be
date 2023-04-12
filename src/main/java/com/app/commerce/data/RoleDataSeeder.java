package com.app.commerce.data;

import com.app.commerce.entity.Role;
import com.app.commerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(value = 1)
public class RoleDataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (roleRepository.count() > 0) {
            return;
        }

        log.info("=> Start importing roles");
        var roles = List.of(
                new Role()
                        .setCode("ROLE_ADMIN")
                        .setName("System administration")
                        .setDescription("Role for system administration"),
                new Role()
                        .setCode("ROLE_MANAGER")
                        .setName("Manager")
                        .setDescription("Role for manager"),
                new Role()
                        .setCode("ROLE_STAFF")
                        .setName("Staff")
                        .setDescription("Role for staff")
        );

        roleRepository.saveAll(roles);
        log.info("=> Finished importing roles");
    }
}

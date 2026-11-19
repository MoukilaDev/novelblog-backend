package com.moukiladev.novelblog.initialConfiguration;

import com.moukiladev.novelblog.entity.Role;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataInitializer implements CommandLineRunner {
    @Value("${application.security.admin.name}")
    private String adminTest;
    @Value("${application.security.admin.password}")
    private String adminPassword;
    @Value("${application.security.admin.email}")
    private String adminEmail;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log =
            LoggerFactory.getLogger(AdminDataInitializer.class);

    public AdminDataInitializer(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if(userRepository.findByUsername(adminTest).isEmpty()){
            if(adminPassword == null || adminPassword.isBlank()){
                throw new IllegalStateException("Invalid configuration : application.security.admin.password (ADMIN_PASSWORD) cannot be null or blank");
            } else if (adminEmail.contains(" ")){
                throw new IllegalStateException("Invalid configuration : application.security.admin.password (ADMIN_PASSWORD) cannot be spaces");
            } else if (adminPassword.length() < 12) {
                throw new IllegalStateException("Invalid configuration : application.security.admin.password (ADMIN_PASSWORD) must be at least 12 characters long" );
            }

            String encodedPassword = passwordEncoder.encode(adminPassword);
            UserEntity adminUser = new UserEntity(
                    adminTest, encodedPassword, adminEmail, Role.ADMIN);

            userRepository.save(adminUser);
        }else{
            log.info("User already exists");
        }
    }
}

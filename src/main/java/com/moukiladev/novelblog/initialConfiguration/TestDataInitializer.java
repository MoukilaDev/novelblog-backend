package com.moukiladev.novelblog.initialConfiguration;

import com.moukiladev.novelblog.entity.Role;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestDataInitializer implements CommandLineRunner {
    @Value("${application.security.admin.name}")
    private String adminTest;
    @Value("${application.security.admin.password}")
    private String adminPassword;
    @Value("${application.security.admin.email}")
    private String adminEmail;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log =
            LoggerFactory.getLogger(DevDataInitializer.class);

    public TestDataInitializer(UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUsername(adminTest).isEmpty()){
            String encodedPassword = passwordEncoder.encode(adminPassword);
            UserEntity adminUser = new UserEntity(
                    adminTest, encodedPassword, adminEmail, Role.ADMIN);

            userRepository.save(adminUser);
        }else{
            log.info("User already exists");
        }
    }
}

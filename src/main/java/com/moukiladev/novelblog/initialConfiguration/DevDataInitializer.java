package com.moukiladev.novelblog.initialConfiguration;

import com.moukiladev.novelblog.entity.Role;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DevDataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log =
            LoggerFactory.getLogger(DevDataInitializer.class);

    public DevDataInitializer (UserRepository userRepository
            , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUsername("userTest").isEmpty()){
            String encodedPassword = passwordEncoder.encode("passwordtest123");
            UserEntity user = new UserEntity("userTest", encodedPassword
                    , "testuser@example.com", Role.ADMIN);

            userRepository.save(user);
        }else{
            log.info("User already exists");
        }
    }
}
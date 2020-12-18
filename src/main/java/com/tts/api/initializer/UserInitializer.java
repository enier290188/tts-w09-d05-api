package com.tts.api.initializer;

import com.tts.api.entity.UserEntity;
import com.tts.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) {
        ArrayList<UserEntity> users = new ArrayList<>(
                Arrays.asList(
                        new UserEntity("James", "Garner", "Ohio"),
                        new UserEntity("Craig", "Steger", "Florida"),
                        new UserEntity("Bruce", "Campbell", "Illinois"),
                        new UserEntity("Louis", "Greene", "Ohio"),
                        new UserEntity("Lawrence", "Spellman", "New Mexico"),
                        new UserEntity("Rita", "Richmond", "Florida"),
                        new UserEntity("Lisa", "Smith", "Ohio"),
                        new UserEntity("Melissa", "Grant", "Vermont"),
                        new UserEntity("Patti", "Simmons", "Maryland"),
                        new UserEntity("Mary", "Jackson", "Floria")
                )
        );
        userRepository.saveAll(users);
    }
}

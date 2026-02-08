package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            // users テーブルが空のときだけ初期ユーザーを作成
            if (userRepository.count() == 0) {

                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRole("ROLE_USER");   // ★ 超重要
                user.setEnabled(true);

                userRepository.save(user);

                System.out.println("初期ユーザー admin を作成しました");
            }
        };
    }
}

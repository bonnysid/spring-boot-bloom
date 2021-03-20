package com.bonnysid.bloom.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User bonnysid = new User("bonnysid", "bonnysidworker@gmail.com", LocalDate.of(2001, APRIL, 22));
            User midorun = new User("midorun", "midorun@gmail.com", LocalDate.of(2001, JANUARY, 10));
            User yanixxxs = new User("yanixxxs", "yanixxxs@gmail.com", LocalDate.of(2004, JANUARY, 1));
            User yuno = new User("yuno", "yuno@gmail.com", LocalDate.of(2001, JULY, 21));
            User faye = new User("faye", "faye@gmail.com", LocalDate.of(2001, JUNE, 19));

            repository.saveAll(List.of(bonnysid,midorun,yanixxxs,yuno,faye));
        };
    }
}

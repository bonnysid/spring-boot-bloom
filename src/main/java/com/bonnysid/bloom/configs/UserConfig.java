package com.bonnysid.bloom.configs;

import com.bonnysid.bloom.model.Post;
import com.bonnysid.bloom.model.Status;
import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.respos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    public CommandLineRunner commandLineRunner(UserRepository repository) {
//        return args -> {
//            User bonnysid = new User("bonnysid", "bonnysidworker@gmail.com","bonnysid", Status.ACTIVE, LocalDate.of(2001, APRIL, 22));
//            bonnysid.setPosts(List.of(new Post("Post 1", "Text1"), new Post("Post 1", "Text1")));
//            User midorun = new User("midorun", "midorun@gmail.com",Status.ACTIVE, LocalDate.of(2001, JANUARY, 10));
//            User yanixxxs = new User("yanixxxs", "yanixxxs@gmail.com",Status.ACTIVE, LocalDate.of(2004, JANUARY, 1));
//            User yuno = new User("yuno", "yuno@gmail.com",Status.ACTIVE, LocalDate.of(2001, JULY, 21));
//            User faye = new User("faye", "faye@gmail.com",Status.BANNED, LocalDate.of(2001, JUNE, 19));
//
//            repository.saveAll(List.of(bonnysid,midorun,yanixxxs,yuno,faye));
//        };
//    }
//}

package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Subscribe;
import com.bonnysid.bloom.respos.SubscribesRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscribesService {
    private final SubscribesRepository subscribesRepository;
    private final UserService userService;

    public SubscribesService(SubscribesRepository subscribesRepository, UserService userService) {
        this.subscribesRepository = subscribesRepository;
        this.userService = userService;
    }

    public boolean checkSubscribe(Long id) {
        return subscribesRepository.findSubscribes(userService.getAuthUsername(), id).isPresent();
    }

    public void follow(Long id) {
        if (checkSubscribe(id)) throw new IllegalStateException("Follow already exist!");
        subscribesRepository.save(new Subscribe(userService.getAuthUsername(), id));
    }

    public void unfollow(Long id) {
        if (!checkSubscribe(id)) throw new IllegalStateException("Follow already not exist!");
        subscribesRepository.delete(new Subscribe(userService.getAuthUsername(), id));
    }

    public List<Long> getAllSubscribes() {
        return subscribesRepository.findAllSubscribes(userService.getAuthUsername())
                .orElse(new ArrayList<>()).stream()
                .map(Subscribe::getIdFollowedUser)
                .collect(Collectors.toList());
    }
}

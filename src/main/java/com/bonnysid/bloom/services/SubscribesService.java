package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Subscribe;
import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.respos.SubscribesRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscribesService {
    private final SubscribesRepository subscribesRepository;

    public SubscribesService(SubscribesRepository subscribesRepository) {
        this.subscribesRepository = subscribesRepository;
    }

    public boolean checkSubscribe(Long id) {
        return subscribesRepository.findSubscribes(getUsername(), id).isPresent();
    }

    public void follow(Long id) {
        if (checkSubscribe(id)) throw new IllegalStateException("Follow already exist!");
        subscribesRepository.save(new Subscribe(getUsername(), id));
    }

    public void unfollow(Long id) {
        if (!checkSubscribe(id)) throw new IllegalStateException("Follow already not exist!");
        subscribesRepository.delete(new Subscribe(getUsername(), id));
    }

    public List<Long> getAllSubscribes() {
        return subscribesRepository.findAllSubscribes(getUsername())
                .orElse(new ArrayList<>()).stream()
                .map(Subscribe::getIdFollowedUser)
                .collect(Collectors.toList());
    }

    private String getUsername() {
        return ((UserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
    }
}

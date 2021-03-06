package com.bonnysid.bloom.services;

import com.bonnysid.bloom.model.Subscribe;
import com.bonnysid.bloom.respos.SubscribesRepository;
import com.bonnysid.bloom.security.AuthInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscribesService {
    private final SubscribesRepository subscribesRepository;
    private final AuthInfo authInfo;

    public SubscribesService(SubscribesRepository subscribesRepository, AuthInfo authInfo) {
        this.subscribesRepository = subscribesRepository;
        this.authInfo = authInfo;
    }

    public boolean checkSubscribe(Long id) {
        if(authInfo.getAuthUsername() == null) return false;
        return subscribesRepository.findSubscribes(authInfo.getAuthUsername(), id).isPresent();
    }

    public void follow(Long id) {
        if (checkSubscribe(id)) throw new IllegalStateException("Follow already exist!");
        subscribesRepository.save(new Subscribe(authInfo.getAuthUsername(), id));
    }

    public void unfollow(Long id) {
        if (!checkSubscribe(id)) throw new IllegalStateException("Follow already not exist!");
        subscribesRepository.delete(new Subscribe(authInfo.getAuthUsername(), id));
    }

    public List<Long> getAllSubscribes() {
        return subscribesRepository.findAllSubscribes(authInfo.getAuthUsername())
                .orElse(new ArrayList<>()).stream()
                .map(Subscribe::getIdFollowedUser)
                .collect(Collectors.toList());
    }
}

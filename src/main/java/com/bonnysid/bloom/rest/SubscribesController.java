package com.bonnysid.bloom.rest;

import com.bonnysid.bloom.model.Subscribe;
import com.bonnysid.bloom.model.User;
import com.bonnysid.bloom.services.SubscribesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/1.0/follow/")
public class SubscribesController {
    private final SubscribesService subscribesService;

    public SubscribesController(SubscribesService subscribesService) {
        this.subscribesService = subscribesService;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public boolean checkSubscribe(@PathVariable long id) {
        return subscribesService.checkSubscribe(id);
    }

    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public void follow(@PathVariable long id) {
        subscribesService.follow(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public void unfollow(@PathVariable long id) {
        subscribesService.unfollow(id);
    }
}

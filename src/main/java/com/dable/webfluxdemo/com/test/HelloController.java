package com.dable.webfluxdemo.com.test;

import com.dable.webfluxdemo.com.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class HelloController {
    private static Map<Long, User> repo = new ConcurrentHashMap<>();

    @GetMapping
    public Mono<String> index(@RequestParam String message) {
        return Mono.just(message);
    }

    @PostMapping("/save/user")
    public Mono<User> save(User user) {
        if (repo.put(user.getId(), user) == null) {
            return Mono.just(user);
        }
        return Mono.just(repo.put(user.getId(), user));
    }

    @GetMapping("/user/list")
    public Collection<User> getList() {
        return repo.values();
    }

    @GetMapping("/user/list/flux")
    public Flux<User> getListFlux() {
        return Flux.fromIterable(repo.values());
    }
}

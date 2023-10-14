package com.lisot.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private final static List<String> ALLOW_LIST = List.of(
            "/auth/register",
            "/auth/token",
            "/eureca");

    public Predicate<ServerHttpRequest> isSecure =
            request -> ALLOW_LIST.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}

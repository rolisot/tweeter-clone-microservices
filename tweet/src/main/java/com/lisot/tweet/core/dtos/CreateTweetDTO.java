package com.lisot.tweet.core.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTweetDTO(
        @NotNull
        UUID userId,
        @NotEmpty(message = "Content is mandatory")
        @NotBlank(message = "Content is mandatory")
        String content) {
}


package com.lisot.tweet.infrastructure.controllers;

import com.lisot.tweet.application.usecases.CreateTweetUseCase;
import com.lisot.tweet.application.usecases.GetAllTweetsByUserUseCase;
import com.lisot.tweet.core.dtos.CreateTweetDTO;
import com.lisot.tweet.core.dtos.TweetDto;
import com.lisot.tweet.domain.entities.Tweet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tweet")
public class TweetController {

    private final CreateTweetUseCase createTweetUseCase;
    private final GetAllTweetsByUserUseCase getAllTweetsByUserUseCase;
    private final ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public List<TweetDto> getAllTweetsByUser(@PathVariable UUID userId){
        return getAllTweetsByUserUseCase.execute(userId)
                .stream()
                .map(tweet -> modelMapper.map(tweet, TweetDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetDto createTweet(@Valid @RequestBody CreateTweetDTO dto){
        Tweet newTweet = createTweetUseCase.execute(dto);
        return modelMapper.map(newTweet, TweetDto.class);
    }
}

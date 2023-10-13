package com.lisot.tweet.infrastructure.repositories;

import com.lisot.tweet.adapters.gateways.TweetGateway;
import com.lisot.tweet.domain.entities.Tweet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TweetRepositoryAdapter implements TweetGateway {

    private final TweetRepository tweetRepository;
    private final ModelMapper mapper;

    @Override
    public Tweet create(Tweet tweet) {
        var model = mapper.map(tweet, TweetModel.class);
        var saved = this.tweetRepository.save(model);
        return mapper.map(saved, Tweet.class);
    }

    @Override
    public List<Tweet> findAllByUserId(UUID userId) {
        return this.tweetRepository.findAllByUserId(userId)
                .stream()
                .map(tweetModel -> mapper.map(tweetModel, Tweet.class))
                .collect(Collectors.toList());
    }
}

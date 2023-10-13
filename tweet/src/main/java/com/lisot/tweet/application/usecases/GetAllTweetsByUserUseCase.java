package com.lisot.tweet.application.usecases;

import com.lisot.tweet.adapters.gateways.TweetGateway;
import com.lisot.tweet.domain.entities.Tweet;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GetAllTweetsByUserUseCase {
    private final TweetGateway tweetGateway;

    public List<Tweet> execute(UUID userId){
        return this.tweetGateway.findAllByUserId(userId);
    }
}

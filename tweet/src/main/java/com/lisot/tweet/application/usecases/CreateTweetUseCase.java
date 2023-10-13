package com.lisot.tweet.application.usecases;

import com.lisot.tweet.core.dtos.CreateTweetDTO;
import com.lisot.tweet.domain.entities.Tweet;
import com.lisot.tweet.adapters.gateways.TweetGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTweetUseCase {

    private final TweetGateway tweetGateway;

    public Tweet execute(CreateTweetDTO input){
        Tweet tweet = Tweet.create(input.content(), input.userId());
        return this.tweetGateway.create(tweet);
    }
}

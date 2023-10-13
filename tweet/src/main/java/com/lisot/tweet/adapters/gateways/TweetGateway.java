package com.lisot.tweet.adapters.gateways;

import com.lisot.tweet.domain.entities.Tweet;

import java.util.List;
import java.util.UUID;

public interface TweetGateway {
    Tweet create(Tweet tweet);
    List<Tweet> findAllByUserId(UUID userId);
}

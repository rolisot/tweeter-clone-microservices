package com.lisot.tweet.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TweetRepository extends JpaRepository<TweetModel, UUID> {
    List<TweetModel> findAllByUserId(UUID userId);
}

package com.lisot.tweet.application.usecases;

import com.lisot.tweet.adapters.gateways.TweetGateway;
import com.lisot.tweet.domain.entities.Tweet;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class GetAllTweetsByUserUseCaseTest {

    @Autowired
    private GetAllTweetsByUserUseCase getAllTweetsByUserUseCase;

    @MockBean
    private TweetGateway tweetGateway;

    @Test
    void shouldGetAllTweetsByUser(){
        var tweetsMock = Instancio.ofList(Tweet.class).size(10).create();

        given(tweetGateway.findAllByUserId(any(UUID.class))).willReturn(tweetsMock);

        var result = getAllTweetsByUserUseCase.execute(UUID.randomUUID());

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(tweetsMock.size());
    }
}
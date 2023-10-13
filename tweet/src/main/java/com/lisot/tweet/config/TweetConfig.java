package com.lisot.tweet.config;

import com.lisot.tweet.adapters.gateways.TweetGateway;
import com.lisot.tweet.application.usecases.CreateTweetUseCase;
import com.lisot.tweet.application.usecases.GetAllTweetsByUserUseCase;
import com.lisot.tweet.infrastructure.repositories.TweetRepository;
import com.lisot.tweet.infrastructure.repositories.TweetRepositoryAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TweetConfig {

    @Bean
    CreateTweetUseCase createTweetUserCase(TweetGateway userGateway) {
        return new CreateTweetUseCase(userGateway);
    }

    @Bean
    GetAllTweetsByUserUseCase getAllTweetsByUserUseCase(TweetGateway userGateway) {
        return new GetAllTweetsByUserUseCase(userGateway);
    }

    @Bean
    TweetGateway createTweetGateway(TweetRepository tweetRepository, ModelMapper mapper){
        return new TweetRepositoryAdapter(tweetRepository, mapper);
    }
}

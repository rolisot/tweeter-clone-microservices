package com.lisot.tweet.application.usecases;

import com.lisot.tweet.core.dtos.CreateTweetDTO;
import com.lisot.tweet.domain.exceptions.TweetCreateException;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CreateTweetUseCaseTest {

    @Autowired
    private CreateTweetUseCase createTweetUseCase;

    @Test
    void shouldCreateTweet(){
        CreateTweetDTO inputDto = Instancio.create(CreateTweetDTO.class);

        var result = createTweetUseCase.execute(inputDto);

        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowAnExceptionWhenFieldsAreNull(){
        CreateTweetDTO inputDto = new CreateTweetDTO(null, null);

        assertThatThrownBy(() -> createTweetUseCase.execute(inputDto))
                .isInstanceOf(TweetCreateException.class);

    }

    @Test
    void shouldThrowAnExceptionWhenContentIsEmpty(){
        CreateTweetDTO inputDto = new CreateTweetDTO(null, "");

        assertThatThrownBy(() -> createTweetUseCase.execute(inputDto))
                .isInstanceOf(TweetCreateException.class);

    }

    @Test
    void shouldThrowAnExceptionWhenContentIsBlank(){
        CreateTweetDTO inputDto = new CreateTweetDTO(null, "  ");

        assertThatThrownBy(() -> createTweetUseCase.execute(inputDto))
                .isInstanceOf(TweetCreateException.class);

    }

}
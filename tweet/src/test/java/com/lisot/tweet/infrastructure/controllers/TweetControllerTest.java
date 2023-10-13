package com.lisot.tweet.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisot.tweet.application.usecases.GetAllTweetsByUserUseCase;
import com.lisot.tweet.core.dtos.CreateTweetDTO;
import com.lisot.tweet.core.dtos.TweetDto;
import com.lisot.tweet.domain.entities.Tweet;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TweetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetAllTweetsByUserUseCase getAllTweetsByUserUseCase;

    @Test
    void shouldGetAllTweetsByUser() throws Exception {
        var mock = Instancio.createList(Tweet.class);

        given(getAllTweetsByUserUseCase.execute(any(UUID.class)))
                .willReturn(mock);

        MvcResult mvcResult = mockMvc.perform(
                get("/api/v1/tweet/{userId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var tweets = objectMapper.readerForListOf(TweetDto.class).readValue(mvcResult.getResponse().getContentAsByteArray());

        assertThat(tweets)
                .usingRecursiveAssertion()
                .hasNoNullFields();
    }

    @Test
    void shouldCreateTweet() throws Exception {
        var createTweetDto = Instancio.create(CreateTweetDTO.class);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTweetDto)))
                .andExpect(status().isCreated())
                .andReturn();

        TweetDto tweetDto = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), TweetDto.class);

        assertThat(tweetDto)
                .usingRecursiveAssertion()
                .hasNoNullFields();

    }

    @Test
    void shouldThrowErrorWhenTryToCreateTweetWithInvalidValues() throws Exception {
        var createTweetDto = new CreateTweetDTO(null, null);

        mockMvc.perform(post("/api/v1/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTweetDto)))
                .andExpect(status().isBadRequest());
    }
}
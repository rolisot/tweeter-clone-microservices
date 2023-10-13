package com.lisot.tweet.domain.entities;

import com.lisot.tweet.domain.exceptions.TweetCreateException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    private UUID id;

    private UUID userId;

    private String content;

    private OffsetDateTime createdAt;

    public static Tweet create(String content, UUID userId){
        validateInput(content, userId);

        return Tweet.builder()
                .content(content)
                .userId(userId)
                .id(UUID.randomUUID())
                .createdAt(OffsetDateTime.now())
                .build();
    }

    private static void validateInput(String content, UUID userId){
        if(userId == null || StringUtils.isEmpty(content) || StringUtils.isBlank(content)){
            throw new TweetCreateException("Content and userId must have valid values.");
        }
    }
}

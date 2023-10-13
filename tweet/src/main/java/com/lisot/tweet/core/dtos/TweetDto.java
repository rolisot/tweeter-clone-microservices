package com.lisot.tweet.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetDto{
    private UUID id;
    private String content;
    private UUID userId;
}

package com.lisot.tweet.infrastructure.repositories;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tweets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetModel {

    @Id
    private UUID id;

    private String content;

    private UUID userId;

    private OffsetDateTime createdAt;
}

package com.lisot.tweet.domain.exceptions;

public class TweetCreateException extends RuntimeException {
    public TweetCreateException(String message){
        super(message);
    }
}

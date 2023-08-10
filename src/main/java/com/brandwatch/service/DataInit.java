package com.brandwatch.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInit {

    private final TweetService tweetService;

    @PostConstruct
    public void init() {
        tweetService.saveTweetList(tweetService.listTweets());
    }
}

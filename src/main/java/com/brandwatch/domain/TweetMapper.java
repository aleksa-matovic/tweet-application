package com.brandwatch.domain;

import com.brandwatch.dto.TweetDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TweetMapper {

    public static List<Tweet> fromDTOList(List<TweetDTO> tweetsDTO) {
        return tweetsDTO.stream()
                .map(TweetMapper::mapToTweet)
                .collect(Collectors.toList());
    }

    public static Tweet mapToTweet(TweetDTO tweetDTO) {
        return Tweet.builder()
                .tweetUrl(tweetDTO.getId())
                .content(tweetDTO.getContent())
                .timestamp(tweetDTO.getTimestamp() == null ? LocalDateTime.now() : tweetDTO.getTimestamp())
                .build();
    }

    public static List<TweetDTO> toDTOList(List<Tweet> tweets) {
        return tweets.stream()
                .map(TweetMapper::mapToTweetDTO)
                .collect(Collectors.toList());
    }

    public static TweetDTO mapToTweetDTO(Tweet tweet) {
        return TweetDTO.builder()
                .id(tweet.getTweetUrl())
                .content(tweet.getContent())
                .timestamp(tweet.getTimestamp())
                .build();
    }
}

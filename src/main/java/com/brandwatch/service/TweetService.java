package com.brandwatch.service;

import com.brandwatch.domain.Tweet;
import com.brandwatch.domain.TweetMapper;
import com.brandwatch.dto.TweetDTO;
import com.brandwatch.storage.TweetRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;


    public TweetDTO saveTweet(TweetDTO tweetDTO) {

        if(getTweetByTweetUrl(tweetDTO.getId()) != null) {
            //throw new RuntimeException("Tweet with the same URL (ID) "  + tweetDTO.getId() + " already exists.");
            return null;
        }

        Tweet savedTweet = tweetRepository.save(TweetMapper.mapToTweet(tweetDTO));

        return TweetMapper.mapToTweetDTO(savedTweet);
    }


    public List<TweetDTO> listTweets() {
        final ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).findAndRegisterModules();

        final File tweets = new File(getClass().getResource("/data/tweets.json").getFile());

        try {
            return objectMapper.readValue(tweets, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Unable to read tweets.", e);
        }
    }

    public void saveTweetList(List<TweetDTO> tweetDTOs) {
        tweetRepository.saveAll(TweetMapper.fromDTOList(tweetDTOs));
    }

    public List<TweetDTO> getAllTweets() {
        return TweetMapper.toDTOList(tweetRepository.findAll());
    }

    public TweetDTO getTweetByTweetUrl(String tweetUrl) {
        Tweet tweet = tweetRepository.findByTweetUrl(tweetUrl);

        return tweet != null ? TweetMapper.mapToTweetDTO(tweet) : null;
    }

    public List<TweetDTO> findTweetsByTimestampRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<Tweet> byTimestampBetween = tweetRepository.findByTimestampBetween(startTime, endTime);

        return TweetMapper.toDTOList(byTimestampBetween);
    }
}

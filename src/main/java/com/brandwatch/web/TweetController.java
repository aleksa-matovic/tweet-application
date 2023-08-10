package com.brandwatch.web;

import java.time.LocalDateTime;
import java.util.List;

import com.brandwatch.dto.TweetDTO;
import com.brandwatch.service.TweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/tweets")
@AllArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @GetMapping
    public ResponseEntity<List<TweetDTO>> tweets() {
        log.info("Get All tweets ");

        List<TweetDTO> allTweets = tweetService.getAllTweets();

        return allTweets != null ? ResponseEntity.ok(allTweets) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/search")
    public ResponseEntity<TweetDTO> searchTweetByTweetUrl(@RequestParam String tweetUrl) {
        log.info("Get Tweet by tweetUrl {} ", tweetUrl);

        TweetDTO tweet = tweetService.getTweetByTweetUrl(tweetUrl);

        return tweet != null ? ResponseEntity.ok(tweet) : ResponseEntity.notFound().build();
    }

    @GetMapping("/bytime")
    public ResponseEntity<List<TweetDTO>> searchTweetsByTimestamp(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        log.info("Search tweet by startTime {} and endTime {} ", startTime, endTime);

        List<TweetDTO> tweetsByTimestampRange = tweetService.findTweetsByTimestampRange(startTime, endTime);

        return tweetsByTimestampRange != null ? ResponseEntity.ok(tweetsByTimestampRange) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TweetDTO> insertTweet(@RequestBody TweetDTO tweetDTO) {
        log.info("New tweet insertion ");

        TweetDTO savedTweet = tweetService.saveTweet(tweetDTO);

        return savedTweet != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedTweet) :
                ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}

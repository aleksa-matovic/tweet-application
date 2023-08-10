package com.brandwatch.storage;

import com.brandwatch.domain.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, String> {

    Tweet findByTweetUrl(String tweetUrl);

    List<Tweet> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);
}

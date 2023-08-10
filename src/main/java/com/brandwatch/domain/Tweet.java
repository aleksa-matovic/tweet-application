package com.brandwatch.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    @Id
    @SequenceGenerator(
            name = "tweet_id_sequence",
            sequenceName = "tweet_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tweet_id_sequence"
    )
    private Long id;
    private String tweetUrl;
    @Column(length = 500)
    private String content;
    @JsonProperty("bw_timestamp")
    private LocalDateTime timestamp;
}

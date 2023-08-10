package com.brandwatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDTO {

    private String id;
    private String content;
    @JsonProperty("bw_timestamp")
    private LocalDateTime timestamp;
}

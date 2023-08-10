package com.brandwatch.javatechnicaltest;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTweets() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tweets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(100)));
    }

    @Test
    public void testSearchTweetByTweetUrlFound() throws Exception {

        String content = "RT @VerbalCommitsD2 2020 Cary-Grove (IL) G Beau Frericks has committed to Lewis. @beaufrericks11 https://t.co/eRGciL8bKJ   http://verbalcommits.com/players/beau-frericks";
        String tweetUrl = "http://twitter.com/Coach_Fish3/statuses/1180633736473391104";
        String timestamp = "2019-10-05T23:59:52";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tweets/search").param("tweetUrl", tweetUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tweetUrl))
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.bw_timestamp").value(timestamp));
    }

    @Test
    public void testSearchTweetByTweetUrlNotFound() throws Exception {
        String tweetUrl = "nepostojeci url" + Math.random();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tweets/search").param("tweetUrl", tweetUrl))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSearchTweetsByTimestamp() throws Exception {
       // 2019-10-05T23:59:50
        LocalDateTime startTime = LocalDateTime.of(2019, 10, 05, 23, 59, 50);
        LocalDateTime endTime = LocalDateTime.of(2019, 10, 05, 23, 59, 50);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tweets/bytime")
                        .param("startTime", startTime.toString())
                        .param("endTime", endTime.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(12)));
    }
}

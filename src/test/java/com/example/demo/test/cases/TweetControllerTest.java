package com.example.demo.test.cases;

import com.example.demo.TweetData;
import com.example.demo.model.APIResponse;
import com.example.demo.model.Root;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.TweetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TweetControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private TweetService tweetService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    static String BASE_API_URL = "/api/v1";

    List<Root> data;


    @BeforeAll
    public void setUpData() {

        try {
            data = objectMapper.readValue(ResourceUtils.getFile("classpath:favs.json"),
                    new TypeReference<List<Root>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getAllTweets() throws Exception {

        Mockito.when(tweetService.getAllTweetData()).thenReturn(TweetData.getAllTweets(data));

        ResponseEntity<APIResponse<List<TweetResponse>>> response = testRestTemplate.
                exchange(BASE_API_URL + "/tweets",HttpMethod.GET,null,
                        new ParameterizedTypeReference<APIResponse<List<TweetResponse>>>() {
                        });

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(),response.getBody().getHttpCode());
        assertEquals(BigInteger.valueOf(311975360667459585L),response.getBody().getBody().get(0).getId());

    }


    @Test
    public void getAllTweetUsers() throws Exception {
        Mockito.when(tweetService.getAllTweetUsers()).thenReturn(TweetData.getAllTweetUsers(data));
        ResponseEntity<APIResponse<List<UserResponse>>> response = testRestTemplate
                .exchange(BASE_API_URL + "/tweets/users", HttpMethod.GET, null
                        , new ParameterizedTypeReference<APIResponse<List<UserResponse>>>() {
                        });

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(),response.getBody().getHttpCode());
        assertEquals(15414807,response.getBody().getBody().get(4).getUserId());
    }

    @Test
    public void getTweetById() throws Exception {

        Mockito.when(tweetService.getTweetById(Mockito.any(BigInteger.class)))
                .thenReturn(TweetData.getTweetResponse(data.get(1)));

        ResponseEntity<APIResponse<TweetResponse>> response = testRestTemplate.
                exchange(BASE_API_URL + "/tweet/{id}", HttpMethod.GET, null
                        , new ParameterizedTypeReference<APIResponse<TweetResponse>>() {
                        },
                        "311964132205268992");


        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(),response.getBody().getHttpCode());
        assertEquals(BigInteger.valueOf(311964132205268992L), response.getBody().getBody().getId());

    }

    @Test
    public void getTweetByIdNotPresent() throws Exception {

        Mockito.when(tweetService.getTweetById(Mockito.any(BigInteger.class)))
                .thenReturn(TweetData.getTweetResponse(data.get(1)));

        ResponseEntity<APIResponse<TweetResponse>> response = testRestTemplate.
                exchange(BASE_API_URL + "/tweet/{id}", HttpMethod.GET, null
                        , new ParameterizedTypeReference<APIResponse<TweetResponse>>() {
                        },
                        "3119641322052689");


        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getBody().getHttpCode());

    }

    @Test
    public void getTweetUserByScreenName() throws Exception {

        Mockito.when(tweetService.getTweetUserByScreenName(Mockito.anyString())).thenReturn(TweetData.getUserResponse(data.get(1)));
        ResponseEntity<APIResponse<UserResponse>> response = testRestTemplate.
                exchange(BASE_API_URL + "/tweet/user?screenName={name}", HttpMethod.GET, null
                        , new ParameterizedTypeReference<APIResponse<UserResponse>>() {
                        }, "MarkUry");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(),response.getBody().getHttpCode());
        assertEquals(2408481,response.getBody().getBody().getUserId());

    }

    @Test
    public void getTweetUserByScreenNameNotPresent() throws Exception {

        Mockito.when(tweetService.getTweetUserByScreenName(Mockito.anyString())).thenReturn(TweetData.getUserResponse(data.get(1)));
        ResponseEntity<APIResponse<UserResponse>> response = testRestTemplate.
                exchange(BASE_API_URL + "/tweet/user?screenName={name}", HttpMethod.GET, null
                        , new ParameterizedTypeReference<APIResponse<UserResponse>>() {
                        }, "jhon");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getBody().getHttpCode());

    }

    @Test
    public void getAllTweetLinks() throws Exception {
        Mockito.when(tweetService.getAllLinks()).thenReturn(TweetData.getAllLinks(data));
        ResponseEntity<APIResponse<Map<BigInteger, List<String>>>> response = testRestTemplate.
                exchange(BASE_API_URL+"/tweet/links", HttpMethod.GET, null
                        , new ParameterizedTypeReference<APIResponse<Map<BigInteger, List<String>>>>() {
                        });

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(),response.getBody().getHttpCode());
        assertThat(response.getBody().getBody()).asString().contains("http://risd.cc/10H8XRE");
        assertThat(response.getBody().getBody()).asString().contains("http://radar.oreilly.com");
        assertThat(response.getBody().getBody()).asString().contains("http://bufferapp.com");
    }


}

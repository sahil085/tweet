package com.example.demo.util;

import com.example.demo.model.Root;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class TweetData {

    @Autowired
    ObjectMapper objectMapper;

    public List<Root> getTweetData() {

        try {
            return objectMapper.readValue(ResourceUtils.getFile("classpath:favs.json"), new TypeReference<List<Root>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

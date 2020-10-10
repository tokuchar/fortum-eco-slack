package com.oncors.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oncors.model.KettleMessage;
import com.oncors.model.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SlackService {

    private RestTemplate restTemplate;
    private ObjectMapper jsonObjectMapper;
    private final String webHookUrl;

    SlackService(@Value("${webhook.url}") String webHookUrl){
        this.webHookUrl = webHookUrl;
        restTemplate = new RestTemplate();
        jsonObjectMapper = new ObjectMapper();
    }



    public void postMessage(SlackMessage slackMessage) throws JsonProcessingException {
        String jsonString = jsonObjectMapper.writeValueAsString(slackMessage);
        ResponseEntity<Void> response = restTemplate.postForEntity(webHookUrl, jsonString, Void.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("request Successful");
        } else {
            log.info("request Failed");
        }
    }
}

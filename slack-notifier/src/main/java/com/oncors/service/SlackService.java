package com.oncors.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oncors.model.KettleMessage;
import com.oncors.model.SlackMessage;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackService {

    private final String webHookUrl = "https://hooks.slack.com/services/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";
    private RestTemplate restTemplate;
    private ObjectMapper jsonObjectMapper;

    SlackService(){
        restTemplate = new RestTemplate();
        jsonObjectMapper = new ObjectMapper();
    }



    public void postMessage(SlackMessage slackMessage) throws JsonProcessingException {
        KettleMessage kettleMessage = new KettleMessage();
        String jsonString = jsonObjectMapper.writeValueAsString(slackMessage);
        ResponseEntity<Void> response = restTemplate.postForEntity(webHookUrl, jsonString, Void.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Kettle request Successful");
        } else {
            System.out.println("Kettle request Failed");
        }
    }
}

package com.oncors.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oncors.model.CloseWindowOnSmog;
import com.oncors.model.CoffeeMessage;
import com.oncors.model.KettleMessage;
import com.oncors.model.OpenWindowColderMessage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@RestClientTest(SlackService.class)
class SlackServiceTest {

    @Autowired
    SlackService slackService;

    @Test
    void kettlePost() throws JsonProcessingException {
        KettleMessage kettleMessage = new KettleMessage();
        slackService.postMessage(kettleMessage);
    }

    @Test
    void coffeePost() throws JsonProcessingException {
        CoffeeMessage coffeeMessage = new CoffeeMessage();
        slackService.postMessage(coffeeMessage);
    }

    @Test
    void openWindowPost() throws JsonProcessingException {
        OpenWindowColderMessage openWindowColderMessage = new OpenWindowColderMessage();
        slackService.postMessage(openWindowColderMessage);
    }

    @Test
    void closeWindowOnSmogPost() throws JsonProcessingException {
        CloseWindowOnSmog closeWindowOnSmog = new CloseWindowOnSmog();
        slackService.postMessage(closeWindowOnSmog);
    }
}

package com.oncors.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        slackService.postKettleReady();
    }
}

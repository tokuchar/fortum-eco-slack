package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Slf4j
@Controller
@EnableScheduling
public class NotifyService {
    @Value("${queue.kettle}")
    private final String kettleQueue;

    @Value("${queue.dishwasher}")
    private final String dishwasherQueue;

    @Value("${queue.coffee}")
    private final String coffeeQueue;

    @Value("${queue.thermometer}")
    private final String thermometerQueue;

    @Value("${queue.smog}")
    private final String smogQueue;

    private final RabbitTemplate rabbitTemplate;
    private final Map<String, DataGenerator> dataGenerators;

    public NotifyService(RabbitTemplate rabbitTemplate,
                         @Value("${queue.kettle}") String kettleQueue,
                         @Value("${queue.dishwasher}") String dishwasherQueue,
                         @Value("${queue.coffee}") String coffeeQueue,
                         @Value("${queue.thermometer}") String thermometerQueue,
                         @Value("${queue.smog}") String smogQueue,
                         Map<String, DataGenerator> generatorMap) {
        this.kettleQueue = kettleQueue;
        this.dishwasherQueue = dishwasherQueue;
        this.coffeeQueue = coffeeQueue;
        this.thermometerQueue = thermometerQueue;
        this.rabbitTemplate = rabbitTemplate;
        this.smogQueue = smogQueue;
        this.dataGenerators = generatorMap;
    }

    @Scheduled(fixedRate = 1000)
    public void notifyAboutKettle() {
        DataGenerator generator = dataGenerators.get("Kettle");
        DeviceEvent event = generator.generate();
        log.info("send to " + kettleQueue + " message " + event.toString());
        rabbitTemplate.convertAndSend(kettleQueue, event);
    }

    @Scheduled(fixedRate = 1000)
    public void notifyAboutDishwasher() {
        DataGenerator generator = dataGenerators.get("Dishwasher");
        DeviceEvent event = generator.generate();
        if(event != null){
            log.info("send to " + dishwasherQueue + " message " + event.toString());
            rabbitTemplate.convertAndSend(dishwasherQueue, event);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void notifyAboutCoffeeExpress() {
        DataGenerator generator = dataGenerators.get("Coffee");
        DeviceEvent event = generator.generate();
        log.info("send to " + coffeeQueue + " message " + event.toString());
        rabbitTemplate.convertAndSend(coffeeQueue, event);
    }

    @Scheduled(fixedRate = (1000*60*3))
    public void notifyAboutThermometer() {
        DataGenerator generator = dataGenerators.get("Thermometer");
        DeviceEvent event = generator.generate();
        log.info("send to " + thermometerQueue + " message " + event.toString());
        rabbitTemplate.convertAndSend(thermometerQueue, event);
    }

    @Scheduled(fixedRate = 1000)
    public void notifyAboutSmog() {
        DataGenerator generator = dataGenerators.get("Smog");
        DeviceEvent event = generator.generate();
        log.info("send to " + smogQueue + " message " + event.toString());
        rabbitTemplate.convertAndSend(smogQueue, event);
    }

}

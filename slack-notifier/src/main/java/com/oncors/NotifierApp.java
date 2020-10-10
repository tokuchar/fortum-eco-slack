package com.oncors;

import com.oncors.model.OpenWindowColderMessage;
import com.oncors.model.OpenWindowWarmerMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NotifierApp {
    public static void main(String... args){
        SpringApplication.run(NotifierApp.class, args);


    }
}

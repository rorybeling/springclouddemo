package com.example.retentionservice;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@EnableBinding(Sink.class)
public class RetentionService {

    @StreamListener(Sink.INPUT)
    public void log(String subscriber) {
        //Call subscription client
        System.out.println(subscriber);
    }
}

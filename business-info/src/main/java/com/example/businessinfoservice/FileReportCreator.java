package com.example.businessinfoservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@EnableBinding(Sink.class)
public class FileReportCreator {

    @Value("filename")
    private String filename;

    @StreamListener(Sink.INPUT)
    public void log(Subscriber subscriber) {
        try (Writer fileWriter = new FileWriter(filename, true)){
            fileWriter.write(subscriber.toString());
        } catch (IOException e) {
//            System.out.println("Problem occurs when deleting the directory : " + filename);
            e.printStackTrace();
        }
    }

}

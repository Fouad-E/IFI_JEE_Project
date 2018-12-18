package com.ifi.tp;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class IHMApp {

    public static void main(String... args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61618");
        broker.setPersistent(false);
        broker.start();

        SpringApplication.run(IHMApp.class, args);
    }
}

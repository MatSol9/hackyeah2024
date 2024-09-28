package com.centaris.razemnazakupy;

import com.centaris.razemnazakupy.notifications.MockPubSubClient;
import com.centaris.razemnazakupy.notifications.PubSubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public PubSubClient pubSubClient() {
        return new MockPubSubClient();
    }
}

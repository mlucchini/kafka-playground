package com.marclucchini.reactor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.bus.EventBus;

@Configuration
public class ReactorConfig {

  @Bean
  EventBus eventBus() {
    return EventBus.create();
  }
}

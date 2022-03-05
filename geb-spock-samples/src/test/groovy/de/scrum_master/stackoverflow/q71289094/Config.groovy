package de.scrum_master.stackoverflow.q71289094

import org.springframework.context.annotation.Bean

class Config {
  @Bean
  Config restTemplate() {
    return new Config()
  }
}

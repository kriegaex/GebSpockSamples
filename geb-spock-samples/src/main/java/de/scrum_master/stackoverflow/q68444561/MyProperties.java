package de.scrum_master.stackoverflow.q68444561;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Data
//@Configuration("myProperties")
//@ConfigurationProperties(prefix = "my")
//@PropertySource("classpath:application.properties")
public class MyProperties {
  private Payments payments;

  @Getter
  @Setter
  public static class Payments {
    private String operator;
    private Period period;

    @Getter
    @Setter
    public static class Period {
      private Duration duration;
    }
  }
}

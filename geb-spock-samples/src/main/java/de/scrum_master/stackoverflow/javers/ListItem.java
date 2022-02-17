package de.scrum_master.stackoverflow.javers;

import lombok.*;
import org.javers.core.metamodel.annotation.Id;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ListItem {
  @Id
  private String itemName;
  private String itemValue;

  public static void main(String[] args) {
    ListItem item = new ListItemBuilder()
      .itemName("mood")
      .itemValue("bored")
      .build();
    System.out.println(item);
  }
}

package de.scrum_master.stackoverflow.javers;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class TopLevelClass {
  List<ListItem> items;
}

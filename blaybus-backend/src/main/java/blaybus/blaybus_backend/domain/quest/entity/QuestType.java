package blaybus.blaybus_backend.domain.quest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestType {

    LEADER_ASSIGNMENT("LEADER_ASSIGNMENT"),
    TASK("TASK");

    private final String description;

}

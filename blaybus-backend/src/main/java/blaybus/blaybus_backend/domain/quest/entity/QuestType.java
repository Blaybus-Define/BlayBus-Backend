package blaybus.blaybus_backend.domain.quest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestType {

    LEADER_ASSIGNMENT("리더부여"),
    TASK("직무");

    private final String description;

}

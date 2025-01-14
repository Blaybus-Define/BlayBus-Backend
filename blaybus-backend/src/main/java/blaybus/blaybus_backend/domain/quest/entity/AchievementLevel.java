package blaybus.blaybus_backend.domain.quest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AchievementLevel {
    NOT_ACHIEVED("달성 안함"),
    MEDIUM("Medium"),
    MAX("Max");

    private final String description;
}

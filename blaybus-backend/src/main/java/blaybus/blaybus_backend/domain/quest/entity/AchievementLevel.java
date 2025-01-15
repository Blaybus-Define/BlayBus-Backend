package blaybus.blaybus_backend.domain.quest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AchievementLevel {
    NOT_ACHIEVED("NOT_ACHIEVED"),
    MEDIUM("MEDIUM"),
    MAX("MAX"),
    FAIL("FAIL");

    private final String value;

    // 문자열 값을 기반으로 AchievementLevel 반환
    public static AchievementLevel fromValue(String value) {
        return Arrays.stream(AchievementLevel.values())
                .filter(level -> level.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid AchievementLevel value: " + value));
    }
}

package blaybus.blaybus_backend.domain.experience;

public enum ExperienceType {
    PERFORMANCE_REVIEW("인사 평가 경험치"),
    LEADER_QUEST("리더 부여 퀘스트 경험치"),
    JOB_QUEST("직무 부여 퀘스트 경험치"),
    PROJECT("전사 프로젝트 경험치");

    private final String description;

    ExperienceType(String description) {
        this.description = description;
    }
}

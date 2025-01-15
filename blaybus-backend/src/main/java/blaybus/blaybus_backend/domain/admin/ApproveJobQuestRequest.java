package blaybus.blaybus_backend.domain.admin;

import lombok.Getter;

@Getter
public class ApproveJobQuestRequest {

    //퀘스트 id
    private Long memberQuestId;
    //달성 정도
    private String AchievementLevel;
}

package blaybus.blaybus_backend.domain.quest.dto;

import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberQuestResponse {

    private List<MemberQuestRecordResponse> quests;
    public MemberQuestResponse(List<MemberQuest> quests) {
        this.quests = quests.stream().map(quest -> MemberQuestRecordResponse.from(quest)).toList();
    }
}

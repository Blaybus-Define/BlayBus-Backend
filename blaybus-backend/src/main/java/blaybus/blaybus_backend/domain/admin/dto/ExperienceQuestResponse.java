package blaybus.blaybus_backend.domain.admin.dto;

import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import lombok.Getter;

import java.util.List;

@Getter
public class ExperienceQuestResponse {
    private List<ExperienceQuestRecordResponse> memberExperienceQuest;

    private ExperienceQuestResponse(List<ExperienceQuestRecordResponse> memberExperienceQuest) {
        this.memberExperienceQuest = memberExperienceQuest;
    }

    public static ExperienceQuestResponse fromMemberQuests(List<MemberQuest> memberQuests) {
        List<ExperienceQuestRecordResponse> responses = memberQuests.stream()
                .map(ExperienceQuestRecordResponse::from)
                .toList();
        return new ExperienceQuestResponse(responses);
    }

    public static ExperienceQuestResponse fromGainExperiences(List<GainExperience> gainExperiences) {
        List<ExperienceQuestRecordResponse> responses = gainExperiences.stream()
                .map(ExperienceQuestRecordResponse::from)
                .toList();
        return new ExperienceQuestResponse(responses);
    }
}

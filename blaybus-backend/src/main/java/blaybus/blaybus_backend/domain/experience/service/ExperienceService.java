package blaybus.blaybus_backend.domain.experience.service;

import blaybus.blaybus_backend.domain.admin.ExperienceQuestRequest;
import blaybus.blaybus_backend.domain.admin.ExperienceQuestResponse;
import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceRepository;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.domain.quest.entity.AchievementLevel;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import blaybus.blaybus_backend.domain.quest.repository.MemberQuestRepository;
import blaybus.blaybus_backend.domain.quest.repository.QuestRepository;
import blaybus.blaybus_backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExperienceService {
    private final MemberRepository memberRepository;
    private final ExperienceRepository experienceRepository;
    private final MemberQuestRepository memberQuestRepository;


    public void grantExperience(ExperienceQuestRequest request) {
        String loginId = request.getLoginId();
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        GainExperience gainExperience = GainExperience.builder()
                .member(member)
                .title(request.getTitle())
                .type(request.getType())
                .date(LocalDate.now())
                .reason(request.getDescription())
                .exp(request.getExperience())
                .build();
        experienceRepository.save(gainExperience);
    }

    public ExperienceQuestResponse findMyExperiences(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        List<GainExperience> experiences = experienceRepository.findAllByMember(member);
        return ExperienceQuestResponse.fromGainExperiences(experiences);
    }

    public ExperienceQuestResponse findNotAchievedOrFailedQuests(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        List<MemberQuest> memberQuests = memberQuestRepository.findAllByMember(member);
        List<MemberQuest> filteredQuests = memberQuests.stream()
                .filter(quest -> quest.getAchievedLevel() == AchievementLevel.NOT_ACHIEVED ||
                        quest.getAchievedLevel() == AchievementLevel.FAIL)
                .collect(Collectors.toList());
        return ExperienceQuestResponse.fromMemberQuests(filteredQuests);
    }
}

package blaybus.blaybus_backend.domain.experience.service;

import blaybus.blaybus_backend.domain.admin.ExperienceQuestRequest;
import blaybus.blaybus_backend.domain.admin.ExperienceQuestResponse;
import blaybus.blaybus_backend.domain.experience.dto.ExpStatusResponseDTO;
import blaybus.blaybus_backend.domain.experience.entity.ExperienceStatus;
import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceRepository;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceStatusRepository;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.domain.quest.entity.AchievementLevel;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import blaybus.blaybus_backend.domain.quest.repository.MemberQuestRepository;
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
    private final ExperienceStatusRepository expRepository;

    // 총 경험치 조회
    public ExpStatusResponseDTO getExpStatusById(Long id) {
        ExperienceStatus expStatus = expRepository.findByMember_Id(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        int annualExp = expStatus.getFirstHalfPerformanceExp() + expStatus.getSecondHalfPerformanceExp()
                    + expStatus.getLeaderExp() + expStatus.getJobRoleExp();

        return ExpStatusResponseDTO.builder()
                .firstHalfPerformanceExp(expStatus.getFirstHalfPerformanceExp())
                .secondHalfPerformanceExp(expStatus.getSecondHalfPerformanceExp())
                .leaderExp(expStatus.getLeaderExp())
                .jobRoleExp(expStatus.getJobRoleExp())
                .annualExp(annualExp)
                .projectExp(expStatus.getProjectExp())
                .previousExp(expStatus.getPreviousExp())
                .build();
    }


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

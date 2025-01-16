package blaybus.blaybus_backend.domain.experience.service;

import blaybus.blaybus_backend.domain.admin.ExperienceQuestRequest;
import blaybus.blaybus_backend.domain.admin.ExperienceQuestResponse;
import blaybus.blaybus_backend.domain.experience.dto.ExpStatusResponseDTO;
import blaybus.blaybus_backend.domain.experience.dto.GainExpResponseDTO;
import blaybus.blaybus_backend.domain.experience.entity.ExperienceStatus;
import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceRepository;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceStatusRepository;
import blaybus.blaybus_backend.domain.experience.repository.GainExperienceRepository;
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
    private final ExperienceStatusRepository expStatusRepository;
    private final GainExperienceRepository gainExpRepository;
    private final ExperienceStatusRepository experienceStatusRepository;

    // 경험치 현황 조회
    public ExpStatusResponseDTO getExpStatusById(Long id) {
        ExperienceStatus expStatus = expStatusRepository.findByMemberId(id)
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

    // 최근 달성한 경험치 조회
    public GainExpResponseDTO getRecentExpByMemberId(Long memberId) {
        GainExperience recentExp = gainExpRepository.findRecentByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("최근 달성한 경험치를 찾을 수 없습니다."));
        return GainExpResponseDTO.builder()
                .title(recentExp.getTitle())
                .type(recentExp.getType())
                .date(recentExp.getDate())
                .reason(recentExp.getReason())
                .exp(recentExp.getExp())
                .description(recentExp.getDescription())
                .build();

    }

    // 전체기간 내 구분에 따른 경험치 달성 목록 조회
    public List<GainExpResponseDTO> getEntireGainExpByMemberIdAndType(Long memberId, String type) {
        List<GainExperience> gainExperiences;
        if("ALL".equalsIgnoreCase(type)) {
            gainExperiences = gainExpRepository.findEntireByMemberId(memberId);
        } else {
            gainExperiences = gainExpRepository.findEntireByMemberIdAndType(memberId, type);
        }

        return gainExperiences.stream()
                .map(GainExpResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 올해 내 구분에 따른 경험치 달성 목록 조회
    public List<GainExpResponseDTO> getAnnualGainExpByMemberIdAndType(Long memberId, String type) {
        List<GainExperience> gainExperiences;
        if("ALL".equalsIgnoreCase(type)) {
            gainExperiences = gainExpRepository.findAnnualByMemberId(memberId);
        } else {
            gainExperiences = gainExpRepository.findAnnualByMemberIdAndType(memberId, type);
        }

        return gainExperiences.stream()
                .map(GainExpResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 지정 기간 내 구분에 따른 경험치 달성 목록 조회
    public List<GainExpResponseDTO> getPeriodGainExpByMemberIdAndType(
            Long memberId, String type, LocalDate start, LocalDate end) {
        List<GainExperience> gainExperiences;
        if("ALL".equalsIgnoreCase(type)) {
            gainExperiences = gainExpRepository.findPeriodByMemberId(memberId, start, end);
        } else {
            gainExperiences = gainExpRepository.findPeriodByMemberIdAndType(memberId, type, start, end);
        }

        return gainExperiences.stream()
                .map(GainExpResponseDTO::fromEntity)
                .collect(Collectors.toList());
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
        member.plusExperience(request.getExperience());
        experienceRepository.save(gainExperience);

        ExperienceStatus expStatus = experienceStatusRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        int plusExp = gainExperience.getExp();
        switch(gainExperience.getType()) {
            case "TASK":
                expStatus.setJobRoleExp(expStatus.getJobRoleExp() + plusExp);
                break;
            case "LEADER_ASSIGNMENT":
                expStatus.setLeaderExp(expStatus.getLeaderExp() + plusExp);
                break;
            case "PERFORMANCE_EVALUATION":
                if(gainExperience.getTitle().contains("상반기")) {
                    expStatus.setFirstHalfPerformanceExp(expStatus.getFirstHalfPerformanceExp() + plusExp);
                } else if (gainExperience.getTitle().contains("하반기")) {
                    expStatus.setSecondHalfPerformanceExp(expStatus.getSecondHalfPerformanceExp() + plusExp);
                } else {
                    throw new IllegalArgumentException("인사평가 경험치 제목이 유효하지 않습니다.");
                }
                break;
            case "CORPORATE_PROJECT":
                expStatus.setProjectExp(expStatus.getProjectExp() + plusExp);
                break;
            default:
                throw new IllegalArgumentException("경험치 유형이 유효하지 않습니다.");
        }
        // 더해진 경험치 반영
        experienceStatusRepository.save(expStatus);
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

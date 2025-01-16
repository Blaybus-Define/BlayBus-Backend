package blaybus.blaybus_backend.domain.experience.service;

import blaybus.blaybus_backend.domain.experience.dto.ExpStatusResponseDTO;
import blaybus.blaybus_backend.domain.experience.dto.GainExpResponseDTO;
import blaybus.blaybus_backend.domain.experience.entity.ExperienceStatus;
import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceStatusRepository;
import blaybus.blaybus_backend.domain.experience.repository.GainExperienceRepository;
import blaybus.blaybus_backend.domain.member.dto.InfoResponseDTO;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.global.common.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExperienceService {
    private SessionManager sessionManager;
    public void findMyAllExperiences(Long memberId) {

    }
    private final ExperienceStatusRepository expStatusRepository;
    private final GainExperienceRepository gainExpRepository;

    @Autowired
    public ExperienceService(ExperienceStatusRepository expStatusRepository,
                             GainExperienceRepository gainExpRepository) {
        this.expStatusRepository = expStatusRepository;
        this.gainExpRepository = gainExpRepository;
    }

    // 경험치 현황 조회
    public ExpStatusResponseDTO getExpStatusById(Long id) {
        ExperienceStatus expStatus = expStatusRepository.findByMember_Id(id)
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
}

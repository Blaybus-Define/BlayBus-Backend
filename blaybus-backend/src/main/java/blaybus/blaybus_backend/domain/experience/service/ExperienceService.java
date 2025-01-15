package blaybus.blaybus_backend.domain.experience.service;

import blaybus.blaybus_backend.domain.experience.dto.ExpStatusResponseDTO;
import blaybus.blaybus_backend.domain.experience.entity.ExperienceStatus;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceStatusRepository;
import blaybus.blaybus_backend.domain.member.dto.InfoResponseDTO;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.global.common.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExperienceService {
    private SessionManager sessionManager;
    public void findMyAllExperiences(Long memberId) {

    }
    private final ExperienceStatusRepository expRepository;

    @Autowired
    public ExperienceService(ExperienceStatusRepository expRepository) {
        this.expRepository = expRepository;
    }

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
}

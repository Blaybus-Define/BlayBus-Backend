package blaybus.blaybus_backend.domain.experience.repository;

import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GainExperienceRepository extends JpaRepository<GainExperience, Long> {
    // 최근 달성한 경험치 반환
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId ORDER BY g.id DESC")
    Optional<GainExperience> findRecentByMemberId(Long memberId);

    // Entire-ALL : 모두 반환
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId ORDER BY g.id ASC")
    List<GainExperience> findEntireByMemberId(Long memberId);

    // Entire-else : 일치하는 타입의 튜플만 반환
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId AND g.type = :type ORDER BY g.id ASC")
    List<GainExperience> findEntireByMemberIdAndType(Long memberId, String type);

    // Annual-All
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId AND FUNCTION('YEAR', g.date) = FUNCTION('YEAR', CURRENT_DATE) ORDER BY g.id ASC")
    List<GainExperience> findAnnualByMemberId(Long memberId);

    // Annual-else
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId AND FUNCTION('YEAR', g.date) = FUNCTION('YEAR', CURRENT_DATE) AND g.type = :type ORDER BY g.id ASC")
    List<GainExperience> findAnnualByMemberIdAndType(Long memberId, String type);

    // Period-All
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId AND g.date BETWEEN :start AND :end ORDER BY g.id ASC")
    List<GainExperience> findPeriodByMemberId(Long memberId, LocalDate start, LocalDate end);

    // Period-else
    @Query("SELECT g FROM GainExperience g WHERE g.member.id = :memberId AND g.date BETWEEN :start AND :end AND g.type = :type ORDER BY g.id ASC")
    List<GainExperience> findPeriodByMemberIdAndType(Long memberId, String type, LocalDate start, LocalDate end);
}

package blaybus.blaybus_backend.domain.quest.repository;

import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.quest.entity.AchievementLevel;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberQuestRepository extends JpaRepository<MemberQuest, Long> {
    List<MemberQuest> findAllByMemberIdAndDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);

    List<MemberQuest> findAllByMember(Member member);

    Optional<MemberQuest> findByIdAndAchievedLevel(Long id, AchievementLevel achievedLevel);
}

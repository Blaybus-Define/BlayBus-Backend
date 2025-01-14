package blaybus.blaybus_backend.domain.quest.repository;

import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberQuestRepository extends JpaRepository<MemberQuest, Long> {
    List<MemberQuest> findAllByMemberId(Long memberId);

    List<MemberQuest> findAllByMemberIdAndDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);
}

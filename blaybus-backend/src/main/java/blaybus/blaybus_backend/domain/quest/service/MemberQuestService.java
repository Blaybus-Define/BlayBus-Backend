package blaybus.blaybus_backend.domain.quest.service;

import blaybus.blaybus_backend.domain.quest.dto.MemberQuestResponse;
import blaybus.blaybus_backend.domain.quest.repository.MemberQuestRepository;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQuestService {
    private final MemberQuestRepository memberQuestRepository;

    //월별 전체 조회
    public MemberQuestResponse getMyQuests(Long memberId, Integer year, Integer month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<MemberQuest> quests = memberQuestRepository.findAllByMemberIdAndDateBetween(memberId, startDate, endDate);
        return new MemberQuestResponse(quests);
    }
}

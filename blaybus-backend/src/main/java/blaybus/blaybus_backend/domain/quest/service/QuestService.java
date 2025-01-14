package blaybus.blaybus_backend.domain.quest.service;

import blaybus.blaybus_backend.domain.quest.controller.QuestSaveRequest;
import blaybus.blaybus_backend.domain.quest.dto.MemberQuestResponse;
import blaybus.blaybus_backend.domain.quest.entity.Quest;
import blaybus.blaybus_backend.domain.quest.entity.QuestType;
import blaybus.blaybus_backend.domain.quest.repository.MemberQuestRepository;
import blaybus.blaybus_backend.domain.quest.entity.MemberQuest;
import blaybus.blaybus_backend.domain.quest.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestService {
    private final MemberQuestRepository memberQuestRepository;
    private final QuestRepository questRepository;

    public MemberQuestResponse getMyQuests(Long memberId, Integer year, Integer month, Integer week) {
        LocalDate startDate;
        LocalDate endDate;
        if (week != null) {
            startDate = LocalDate.of(year, month, 1).plusWeeks(week - 1);
            endDate = startDate.plusDays(6);
        } else {
            startDate = LocalDate.of(year, month, 1);
            endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        }
        List<MemberQuest> quests = memberQuestRepository.findAllByMemberIdAndDateBetween(memberId, startDate, endDate);
        quests = quests.stream()
                .sorted(Comparator.comparing(MemberQuest::getAchievedLevel))
                .collect(Collectors.toList());
        return new MemberQuestResponse(quests);
    }

    public void createJobQuest(QuestSaveRequest questSaveRequest) {
        Quest quest = questSaveRequest.toQuest(QuestType.TASK);
        questRepository.save(quest);
    }

    public void createLeaderQuest(QuestSaveRequest questSaveRequest) {
        Quest quest = questSaveRequest.toQuest(QuestType.LEADER_ASSIGNMENT);
        questRepository.save(quest);
    }
}

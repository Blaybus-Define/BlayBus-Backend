package blaybus.blaybus_backend.domain.quest.service;

import blaybus.blaybus_backend.domain.admin.ApproveQuestRequest;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.domain.quest.entity.*;
import blaybus.blaybus_backend.domain.quest.exception.QuestException;
import blaybus.blaybus_backend.domain.quest.controller.QuestSaveRequest;
import blaybus.blaybus_backend.domain.quest.dto.MemberQuestResponse;
import blaybus.blaybus_backend.domain.quest.repository.MemberQuestRepository;
import blaybus.blaybus_backend.domain.quest.repository.QuestRepository;
import blaybus.blaybus_backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestService {
    private final MemberQuestRepository memberQuestRepository;
    private final QuestRepository questRepository;
    private final MemberRepository memberRepository;

    public MemberQuestResponse getMyQuests(Long memberId, Integer year, Integer month, Integer week) {
        LocalDate startDate;
        LocalDate endDate;
        if (week != null) {
            startDate = LocalDate.of(year, month, 1).plusWeeks(week - 1);
            endDate = startDate.plusDays(6);
            if (startDate.getMonthValue() != month) {
                throw new IllegalArgumentException("Invalid week number for the given month.");
            }
            if (endDate.getMonthValue() != month) {
                endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
            }
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
        assignMemberQuests(quest, questSaveRequest.getLoginIds());

    }

    public void createLeaderQuest(QuestSaveRequest questSaveRequest) {
        Quest quest = questSaveRequest.toQuest(QuestType.LEADER_ASSIGNMENT);
        questRepository.save(quest);
        assignMemberQuests(quest, questSaveRequest.getLoginIds());
    }

    private void assignMemberQuests(Quest quest, List<String> loginIds) {
        LocalDate today = LocalDate.now();
        LocalDate yearEnd = LocalDate.of(today.getYear(), 12, 31);

        List<LocalDate> questDates = getQuestDates(quest.getFrequency(), today, yearEnd);
        List<Member> members = memberRepository.findByLoginIdIn(loginIds);

        for (LocalDate date : questDates) {
            for (Member member : members) {
                MemberQuest memberQuest = MemberQuest.builder()
                        .member(member)
                        .quest(quest)
                        .date(date)
                        .build();
                memberQuestRepository.save(memberQuest);
            }
        }
    }

    private List<LocalDate> getQuestDates(QuestFrequency frequency, LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();

        if (frequency == QuestFrequency.WEEKLY) {
            LocalDate nextMonday = start.plusWeeks(1).with(java.time.DayOfWeek.MONDAY);
            LocalDate current = nextMonday;
            while (!current.isAfter(end)) {
                dates.add(current);
                current = current.plusWeeks(1);
            }
        } else if (frequency == QuestFrequency.MONTHLY) {
            LocalDate nextMonthFirst = start.plusMonths(1).withDayOfMonth(1);
            LocalDate current = nextMonthFirst;
            while (!current.isAfter(end)) {
                dates.add(current);
                current = current.plusMonths(1);
            }
        }
        return dates;
    }

    public MemberQuestResponse getMemberQuests(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        List<MemberQuest> memberQuest = memberQuestRepository.findAllByMember(member);
        return new MemberQuestResponse(memberQuest);
    }

    public void approveJobQuest(ApproveQuestRequest approveJobQuestRequest) {
        Long memberQuestId = approveJobQuestRequest.getMemberQuestId();
        MemberQuest memberQuest = memberQuestRepository.findByIdAndAchievedLevel(memberQuestId, AchievementLevel.NOT_ACHIEVED)
                .orElseThrow(() -> new QuestException(ErrorCode.ALREADY_COMPLETED));

        AchievementLevel achievementLevel = AchievementLevel.fromValue(approveJobQuestRequest.getAchievementLevel());
        memberQuest.updateAchievedLevel(achievementLevel);
        int experience = switch (achievementLevel) {
            case MAX -> memberQuest.getQuest().getMaxCriterionExperience();
            case MEDIUM -> memberQuest.getQuest().getMediumCriterionExperience();
            default -> 0;
        };
        if (experience > 0) {
            memberQuest.getMember().plusExperience(experience);
        }

    }
}

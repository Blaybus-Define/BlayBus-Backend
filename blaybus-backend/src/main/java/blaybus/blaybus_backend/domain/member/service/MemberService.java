package blaybus.blaybus_backend.domain.member.service;

import blaybus.blaybus_backend.domain.admin.MemberResponse;
import blaybus.blaybus_backend.domain.experience.repository.ExperienceRepository;
import blaybus.blaybus_backend.domain.member.dto.*;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ExperienceRepository experienceRepository;

    // 사용자 정보 조회
    public InfoResponseDTO getInfoById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return InfoResponseDTO.builder()
                .employeeNumber(member.getEmployeeNumber())
                .name(member.getName())
                .hireDate(member.getHireDate())
                .department(member.getJobInfo().getDepartment())
                .jobGroup(member.getJobInfo().getJobGroup())
                .loginId(member.getLoginId())
                .totalExperience(member.getTotalExperience())
                .levelName(member.getLevel())
                .profileCharacter(member.getProfileCharacter())
                .build();

    }

    // 비밀번호 변경
    public UpdatePwResponseDTO updatePw(Long id, UpdatePwRequestDTO updateRequest) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if(!member.getPassword().equals(updateRequest.getOldPassword())) {
            return new UpdatePwResponseDTO("비밀번호가 일치하지 않습니다.");
        }
        member.setPassword(updateRequest.getNewPassword());
        memberRepository.save(member);

        return new UpdatePwResponseDTO("비밀번호가 변경되었습니다.");

    }

    // 프로필 캐릭터 변경
    public UpdatePCharResponseDTO updatePChar(Long id, UpdatePCharRequestDTO updateRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        member.setProfileCharacter(updateRequest.getProfileCharacter());
        memberRepository.save(member);

        return new UpdatePCharResponseDTO();
    }

    public MemberResponse findMemberByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.from(member);
    }
}

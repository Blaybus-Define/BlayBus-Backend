package blaybus.blaybus_backend.domain.member.service;

import blaybus.blaybus_backend.domain.member.dto.UpdatePwRequestDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwResponseDTO;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import blaybus.blaybus_backend.domain.member.dto.InfoResponseDTO;
import blaybus.blaybus_backend.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 사용자 정보 조회
    public InfoResponseDTO getInfoById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return InfoResponseDTO.builder()
                .employeeNumber(member.getEmployeeNumber())
                .name(member.getName())
                .hireDate(member.getHireDate())
                .department(member.getDepartment())
                .levelName("")
                .build();

    }

    // 비밀번호 변경
    public UpdatePwResponseDTO updatePw(UpdatePwRequestDTO updateRequest) {

        Member member = memberRepository.findByLoginId(updateRequest.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if(!member.getPassword().equals(updateRequest.getOldPassword())) {
            return new UpdatePwResponseDTO("비밀번호가 일치하지 않습니다.");
        }
        member.setPassword(updateRequest.getNewPassword());
        memberRepository.save(member);

        return new UpdatePwResponseDTO("비밀번호가 변경되었습니다.");
    }

}

package blaybus.blaybus_backend.domain.member.controller;

import blaybus.blaybus_backend.domain.member.dto.InfoResponseDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwRequestDTO;
import blaybus.blaybus_backend.domain.member.dto.UpdatePwResponseDTO;
import blaybus.blaybus_backend.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<InfoResponseDTO> getInfo(@PathVariable Long id) {
        InfoResponseDTO memberInfo = memberService.getInfoById(id);
        return ResponseEntity.ok(memberInfo);
    }

    @PutMapping("/update-pw")
    public ResponseEntity<UpdatePwResponseDTO> updatePw(
            @RequestBody UpdatePwRequestDTO updateRequest) {
        UpdatePwResponseDTO updateResponse = memberService.updatePw(updateRequest);
        return ResponseEntity.ok(updateResponse);
    }

}

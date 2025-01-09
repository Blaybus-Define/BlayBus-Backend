package blaybus.blaybus_backend.global.security;

import blaybus.blaybus_backend.domain.member.entity.Member;
import blaybus.blaybus_backend.domain.member.exception.MemberException;
import blaybus.blaybus_backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmployeeNumber(username)
                .orElseThrow(() -> new MemberException("해당 사용자를 찾을 수 없습니다"));

        return User.builder()
                .username(member.getEmployeeNumber())
                .password(member.getPassword()) // 암호화된 비밀번호 저장
                .roles("USER") // 사용자 역할 부여
                .build();
    }
}

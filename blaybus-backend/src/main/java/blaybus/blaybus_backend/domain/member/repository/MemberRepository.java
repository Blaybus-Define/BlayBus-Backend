package blaybus.blaybus_backend.domain.member.repository;

import blaybus.blaybus_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findById(Long id);

    boolean existsByEmployeeNumber(String employeeNumber);
}

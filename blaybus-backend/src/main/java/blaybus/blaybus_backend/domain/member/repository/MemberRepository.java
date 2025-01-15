package blaybus.blaybus_backend.domain.member.repository;

import blaybus.blaybus_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

    boolean existsByEmployeeNumber(String employeeNumber);

    List<Member> findByEmployeeNumberIn(List<String> employeeNumbers);
}

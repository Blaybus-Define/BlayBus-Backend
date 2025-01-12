package blaybus.blaybus_backend.domain.member.repository;

import blaybus.blaybus_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

    @Query("SELECT ml.levelName FROM Member m JOIN m.jobRole jr JOIN MemberLevel ml ON ml.jobRole.id = jr.id WHERE m.id = :id")
    String findLevelNameById(@Param("id") Long id);

    boolean existsByEmployeeNumber(String employeeNumber);
}

package blaybus.blaybus_backend.domain.experience.repository;

import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import blaybus.blaybus_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<GainExperience, Long> {
    @Query("SELECT g FROM GainExperience g WHERE g.member = :member")
    List<GainExperience> findAllByMember(@Param("member") Member member);
}

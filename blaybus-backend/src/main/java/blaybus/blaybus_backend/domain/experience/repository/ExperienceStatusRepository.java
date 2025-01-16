package blaybus.blaybus_backend.domain.experience.repository;

import blaybus.blaybus_backend.domain.experience.entity.ExperienceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExperienceStatusRepository extends JpaRepository<ExperienceStatus, Long> {
    Optional<ExperienceStatus> findByMember_Id(Long memberId);

}

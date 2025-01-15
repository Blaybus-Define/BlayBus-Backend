package blaybus.blaybus_backend.domain.experience.repository;

import blaybus.blaybus_backend.domain.experience.entity.GainExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<GainExperience, Long> {
}

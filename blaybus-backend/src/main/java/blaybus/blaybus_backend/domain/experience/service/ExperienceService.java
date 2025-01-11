package blaybus.blaybus_backend.domain.experience.service;

import blaybus.blaybus_backend.global.common.SessionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExperienceService {
    private SessionManager sessionManager;
    public void findMyAllExperiences(Long memberId) {

    }
}

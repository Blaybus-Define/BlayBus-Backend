package blaybus.blaybus_backend.global.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import static blaybus.blaybus_backend.domain.auth.controller.SessionConst.MEMBER_ID;

@Component
//유틸리티 클래스로 하는게 나을지도..?
public class SessionManager {

    public Long getMemberId(HttpSession session) {
        return (Long) session.getAttribute(MEMBER_ID);
    }

}

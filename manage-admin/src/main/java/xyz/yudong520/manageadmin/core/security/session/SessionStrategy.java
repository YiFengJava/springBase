package xyz.yudong520.manageadmin.core.security.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class SessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        log.info("并发登陆");
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("并发登陆");
    }
}

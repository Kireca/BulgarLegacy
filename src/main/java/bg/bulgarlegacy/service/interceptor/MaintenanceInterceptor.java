package bg.bulgarlegacy.service.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;

@Configuration
public class MaintenanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        var requestURI = request.getRequestURI();
        if (!requestURI.equals("/maintenance")) {
            LocalTime now = LocalTime.now();
            if (now.getHour() >= 13 && now.getHour() <= 14) {
                //TODO between 2 and 3!
                response.sendRedirect("/maintenance");
                return false;
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
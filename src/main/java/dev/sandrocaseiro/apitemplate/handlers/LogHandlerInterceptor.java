package dev.sandrocaseiro.apitemplate.handlers;

import dev.sandrocaseiro.apitemplate.services.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LogHandlerInterceptor extends HandlerInterceptorAdapter {
    private final LogService logService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logService.clear();
    }
}

package dev.profitsoft.hw4.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Web filter, which checks if user has authority to access some pages
 * (for purposes of this task - only 2 pages: "/homepage" and "/users").
 * If session attribute "current_user" is null and requested page is one of the mentioned above,
 * then user will be redirected to the login page. Otherwise, filter gives control to filter chain.
 */
@Component
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object currentUser = request.getSession().getAttribute("current_user");
        String requestURI = request.getRequestURI();

        if (currentUser == null && (requestURI.startsWith("/homepage") || requestURI.startsWith("/users"))) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
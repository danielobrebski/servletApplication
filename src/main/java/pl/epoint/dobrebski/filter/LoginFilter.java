package pl.epoint.dobrebski.filter;

import pl.epoint.dobrebski.utils.Constants;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dobrebski on 05.01.17.
 */

@WebFilter(filterName = "loginFilter", urlPatterns={"/list", "/edit"}, initParams = {
        @WebInitParam(name = "username", value = "user"),
        @WebInitParam(name = "password", value = "123456")}
)
public class LoginFilter implements Filter {

    private String username;
    private String password;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        username = filterConfig.getInitParameter("username");
        password = filterConfig.getInitParameter("password");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userId = checkUser(request, httpServletRequest);
        if(userId == null) {
            request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response);
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

    private String checkUser(ServletRequest request, HttpServletRequest httpServletRequest) {
        String usernameInput = request.getParameter("username");
        String passwordInput = request.getParameter("password");
        Boolean invalidate = Boolean.parseBoolean(request.getParameter("invalidate"));
        if(httpServletRequest.getSession() == null) {
            return null;
        }

        String userId = (String) httpServletRequest.getSession().getAttribute("userId");

        if(invalidate != null && invalidate == true) {
            httpServletRequest.getSession().invalidate();
            return null;
        } else if(usernameInput != null && usernameInput.equals(username) && passwordInput != null && passwordInput.equals(password)) {
            httpServletRequest.getSession().setAttribute("userId", username);
            userId = username;
        }
        return userId;
    }

    @Override
    public void destroy() {
        username = null;
        password = null;
    }
}

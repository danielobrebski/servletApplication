package pl.epoint.dobrebski.servletsample;

/**
 * Created by dobrebski on 04.01.17.
 */

import pl.epoint.dobrebski.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="listServlet", urlPatterns={"/list"})
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static Integer hitCounter = 0;
    private Integer sessionHitCounter = 0;
    private Integer servletContextHitCounter = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCounters(request);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher(Constants.MANAGE_PRODUCTS_JSP);
        rd.forward(request, response);
    }

    private void setCounters(HttpServletRequest request) {
        setGlobalHitCounter(request);
        setSessionHitCounter(request);
        setServletContextHitCounter(request);
    }

    private void setGlobalHitCounter(HttpServletRequest request) {
        hitCounter++;
        request.setAttribute("counter", hitCounter);
    }

    private void setSessionHitCounter(HttpServletRequest request) {
        HttpSession session = request.getSession();
        sessionHitCounter = (Integer) session.getAttribute("sessionCounter");

        if (sessionHitCounter == null) {
            sessionHitCounter = 1;
        } else {
            sessionHitCounter++;
        }
        session.setAttribute("sessionCounter", sessionHitCounter);
    }

    private void setServletContextHitCounter(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        servletContextHitCounter = (Integer) servletContext.getAttribute("servletContextCounter");
        if(servletContextHitCounter == null) {
            servletContextHitCounter = 1;
        } else {
            servletContextHitCounter++;
        }
        servletContext.setAttribute("servletContextCounter", servletContextHitCounter);
    }
}

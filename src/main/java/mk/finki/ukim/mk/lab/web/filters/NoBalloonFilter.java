package mk.finki.ukim.mk.lab.web.filters;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//todo
@WebFilter
@Profile("servlet")
public class NoBalloonFilter implements Filter {

    private List<String> allowedSites = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.allowedSites.add("/balloons");
        this.allowedSites.add("/selectBalloon");
        this.allowedSites.add("/BalloonOrder.do");
        this.allowedSites.add("/ConfirmationInfo");
        this.allowedSites.add("/main.css");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();
        String balloonColor = request.getParameter("color");

        Balloon b = (Balloon) request.getSession().getAttribute("bColor");

        User user = (User) request.getSession().getAttribute("user");



//        if(!path.equals("/balloons") && b == null && balloonColor == null) {
//            response.sendRedirect("/balloons");
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }

        if(!"/login".equals(path) &&
                !"/register".equals(path) &&
                !"main.css".equals(path) &&
                user == null) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

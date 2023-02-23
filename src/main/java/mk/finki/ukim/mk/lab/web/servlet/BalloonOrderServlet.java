package mk.finki.ukim.mk.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="balloon-order-servlet", urlPatterns = "/BalloonOrder.do")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String balloonSize = req.getParameter("size");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String clientAddress = req.getRemoteAddr();
        String clientName = req.getHeader("User-Agent");

        context.setVariable("balloonSize", balloonSize);

        context.setVariable("clientAddress", clientAddress);
        context.setVariable("clientName",clientName);

        req.getSession().setAttribute("cAddr",clientAddress);
        req.getSession().setAttribute("cName",clientName);
        req.getSession().setAttribute("bSize",balloonSize);

        this.springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }
}

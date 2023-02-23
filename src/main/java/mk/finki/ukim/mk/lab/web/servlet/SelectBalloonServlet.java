package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.impl.BalloonServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="select-balloon-servlet",urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloonColor = req.getParameter("color");


        WebContext wb = new WebContext(req, resp, req.getServletContext());
        wb.setVariable("balloonColor",balloonColor);

        Balloon b = null;

        try {
            b = this.balloonService.add(balloonColor);
        } catch (Exception e) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("balloonNoColor", true);
            context.setVariable("errMsg","You have to select a color for the balloon!");
            this.springTemplateEngine.process("listBalloons.html",context, resp.getWriter());
        }


        req.getSession().setAttribute("bColor",balloonColor);
        req.getSession().setAttribute("b",balloonColor);
        this.springTemplateEngine.process("selectBalloonSize.html",wb, resp.getWriter());



    }
}

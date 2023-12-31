package step.learning.servlets;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/filters") // другий спосіб (після web.xml) роутингу сервлетів
@Singleton
public class FiltersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().print("FiltersServlet");
        req.setAttribute("page-body", "filters.jsp");
        req.getRequestDispatcher("WEB-INF/_layout.jsp")
                .forward(req,resp); // ~ return View()
    }
}

package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoImpl;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final long serialVersionUID = 1L;
    private MealsDaoImpl dao;

    public MealServlet() {
        super();
        dao = new MealsDaoImpl() {};
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        request.setAttribute("meals", MealsUtil.getWithExcess(MealsUtil.MEALS));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

        String action = request.getParameter("action");

        switch (action) {
            case "delete":
                dao.deleteMeal(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
                break;
            case "update":
                MealTo meal = dao.getById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
            default:
                request.setAttribute("meals", MealsUtil.getWithExcess(MealsUtil.MEALS));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO
    }
}

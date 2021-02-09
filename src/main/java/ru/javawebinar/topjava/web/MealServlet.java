package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealDAOImpl listDAO = new MealDAOImpl();
    private final int CALORIES_PER_DAY = 2000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = req.getParameter("action");

        switch (action == null ? "info" : action) {
            case "update":
                long mealToBeUpdatedId = Integer.parseInt(req.getParameter("id"));
                Meal meal = listDAO.getById(mealToBeUpdatedId);
                listDAO.update(mealToBeUpdatedId, meal);
                req.getRequestDispatcher("/add.jsp").forward(req, resp);
                break;
            case "delete":
                long mealToBeDeletedId = Integer.parseInt(req.getParameter("id"));
                listDAO.delete(mealToBeDeletedId);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
            default:
                List<MealTo> mealsWithExcess = MealsUtil.filteredByStreams(listDAO.getAll(), LocalTime.of(0, 0),
                        LocalTime.of(23, 59), CALORIES_PER_DAY);
                req.setAttribute("mealsWithExcess", mealsWithExcess);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        req.setCharacterEncoding("UTF-8");

        LocalDateTime dateTime = TimeUtil.parseDate(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(++MealDAOImpl.mealsCount, dateTime, description, calories);
        listDAO.add(meal);
        List<MealTo> mealsWithExcess = MealsUtil.filteredByStreams(listDAO.getAll(), LocalTime.of(0, 0),
                LocalTime.of(23, 59), CALORIES_PER_DAY);

        req.setAttribute("mealsWithExcess", mealsWithExcess);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}

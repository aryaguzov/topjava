package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsAsListDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealsAsListDAO mealsAsListDAO = new MealsAsListDAO();
    private final List<Meal> meals = mealsAsListDAO.getAllMeals();
    private final int CALORIES_PER_DAY = 2000;
    private final List<MealTo> mealsWithExcess = MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0),
            LocalTime.of(23, 59), CALORIES_PER_DAY);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        req.setAttribute("mealsWithExcess", mealsWithExcess);

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

}

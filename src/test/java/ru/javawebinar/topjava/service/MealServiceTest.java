package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.NOT_FOUND_MEAL;
import static ru.javawebinar.topjava.UserTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Meal mealToBeGotten = mealService.get(MEAL_ID, USER_ID);
        assertMatch(mealToBeGotten, meal);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.get(NOT_FOUND_MEAL, USER_ID));
    }

    @Test
    public void getIfNotAnOwner() {
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        mealService.delete(MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.delete(NOT_FOUND_MEAL, USER_ID));
    }

    @Test
    public void deleteIfNotAnOwner() {
        assertThrows(NotFoundException.class, () -> mealService.delete(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        LocalDate startDate = LocalDate.of(2021, Month.FEBRUARY, 19);
        LocalDate endDate = LocalDate.of(2021, Month.FEBRUARY, 20);
        List<Meal> actual = mealService.getBetweenInclusive(startDate, endDate, USER_ID);
        List<Meal> expected = getBetween();
        assertMatch(actual, expected);
    }

    @Test
    public void getAll() {
        List<Meal> actual = mealService.getAll(USER_ID);
        List<Meal> expected = Arrays.asList(meal, meal2, meal4);
        assertMatch(actual, expected);
    }

    @Test
    public void update() {
        Meal updated = getToBeUpdated();
        mealService.update(updated, USER_ID);
        assertMatch((mealService.get(updated.getId(), USER_ID)), getToBeUpdated());
    }

    @Test
    public void updateIfNotAnOwner() {
        assertThrows(NotFoundException.class, () -> mealService.update(meal, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = mealService.create(MealTestData.getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = MealTestData.getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch((mealService.get(newId, USER_ID)), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                mealService.create(new Meal(null, LocalDateTime.of(2021, Month.FEBRUARY, 18, 12, 0),
                        "newDescription", 1250), USER_ID));
    }
}
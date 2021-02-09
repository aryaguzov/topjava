package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MealDAOImpl implements MealDAO {
    public static ArrayList<Meal> mealList;
    public static int mealsCount;

    {
        mealList = new ArrayList<>(Collections.synchronizedList(Arrays.asList(
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(++mealsCount, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        )));
    }

    @Override
    public void add(Meal meal) {
        mealList.add(new Meal(++mealsCount, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public void delete(long mealId) {
        mealList.removeIf(m -> m.getId() == mealId);
    }

    @Override
    public void update(long id, Meal updatedMeal) {
        Meal mealToBeUpdated = getById(id);

        mealToBeUpdated.setId(updatedMeal.getId());
        mealToBeUpdated.setDateTime(updatedMeal.getDateTime());
        mealToBeUpdated.setDescription(updatedMeal.getDescription());
        mealToBeUpdated.setCalories(updatedMeal.getCalories());
    }

    @Override
    public Meal getById(long mealId) {
        return mealList.stream()
                .filter(meal -> meal.getId() == mealId)
                .findAny().orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return mealList;
    }
}

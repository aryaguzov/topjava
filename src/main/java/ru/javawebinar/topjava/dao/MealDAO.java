package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {
    void add(Meal meal);

    void delete(long mealId);

    void update(long id, Meal meal);

    Meal getById(long mealId);

    List<Meal> getAll();
}

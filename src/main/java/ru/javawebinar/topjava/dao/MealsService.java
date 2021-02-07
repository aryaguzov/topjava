package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsService {
    void addMeal(Meal meal);

    void deleteMeal(int mealId);

    void updateMeal(Meal meal);

    List<Meal> getAllMeals();
}

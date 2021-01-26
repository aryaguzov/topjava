package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 28, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 27, 10, 0), "Завтрак", 2500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 26, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 25, 10, 0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 24, 10, 0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 29, 10, 0), "Завтрак", 1500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 7, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 8, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 11, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapWithCaloriesPerDay = new HashMap<>();

        for (UserMeal mealToMergeWithMap : meals) {
            mapWithCaloriesPerDay.merge(LocalDate.from(mealToMergeWithMap.getDateTime()), mealToMergeWithMap.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> filtered = new ArrayList<>();

        for (UserMeal meal : meals) {

            boolean checkBetweenHalfOpen = TimeUtil.isBetweenHalfOpen(LocalTime.from(meal.getDateTime()), startTime, endTime);
            int dailyCalories = mapWithCaloriesPerDay.get(LocalDate.from(meal.getDateTime()));

            if (checkBetweenHalfOpen) {
                filtered.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), dailyCalories > caloriesPerDay));
            }
        }

        return filtered;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // TODO Implement by streams
        return null;
    }
}

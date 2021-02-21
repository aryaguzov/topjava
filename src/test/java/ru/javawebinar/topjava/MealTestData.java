package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID = AbstractBaseEntity.START_SEQ + 2;

    public static final int NOT_FOUND_MEAL = 20;

    public static final Meal meal = new Meal(MEAL_ID, LocalDateTime.of(2021, Month.FEBRUARY, 18, 12, 0), "Обед", 1000);
    public static final Meal meal1 = new Meal(MEAL_ID + 1, LocalDateTime.of(2021, Month.FEBRUARY, 18, 18, 0), "Ужин", 250);
    public static final Meal meal2 = new Meal(MEAL_ID + 2, LocalDateTime.of(2021, Month.FEBRUARY, 19, 8, 0), "Завтрак", 200);
    public static final Meal meal3 = new Meal(MEAL_ID + 3, LocalDateTime.of(2021, Month.FEBRUARY, 19, 8, 30), "Завтрак", 200);
    public static final Meal meal4 = new Meal(MEAL_ID + 4, LocalDateTime.of(2021, Month.FEBRUARY, 20, 10, 0), "Ужин", 200);
    public static final Meal meal5 = new Meal(MEAL_ID + 5, LocalDateTime.of(2021, Month.FEBRUARY, 20, 16, 0), "Полдник", 200);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2000, Month.APRIL, 12, 0, 0), "some meal", 1900);
    }

    public static Meal getToBeUpdated() {
        Meal mealToBeUpdated = new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mealToBeUpdated.setDateTime(LocalDateTime.of(1990, Month.APRIL, 12, 2, 30));
        mealToBeUpdated.setDescription("Some night meal");
        mealToBeUpdated.setCalories(1500);
        return mealToBeUpdated;
    }

    public static List<Meal> getBetween() {
        return Arrays.asList(meal2, meal4);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}

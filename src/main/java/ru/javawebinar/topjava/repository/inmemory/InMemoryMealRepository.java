package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal);
        int mealId = 0;
        if (meal.isNew()) {
            mealId = counter.incrementAndGet();
            meal.setId(mealId);
        } else if (get(mealId, userId) == null) {
            return null;
        }
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        meals.put(mealId, meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        final Comparator<Meal> MEAL_BY_DATE_COMPARATOR = Comparator.comparing(Meal::getDateTime).reversed();
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? Collections.emptyList() :
                meals.values().stream()
                        .sorted(MEAL_BY_DATE_COMPARATOR)
                        .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getAllFiltered(LocalDate startDay, LocalDate endDay, int userId) {
        Objects.requireNonNull(startDay);
        Objects.requireNonNull(endDay);
        return getAll(userId).stream()
                .filter(um -> DateTimeUtil.isBetweenHalfOpen(um.getDate(), startDay, endDay))
                .collect(Collectors.toList());
    }
}


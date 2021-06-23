package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else {
            Meal userMeal = get(meal.getId(), userId);
            if (userMeal == null) return null;
        }
        meal.setUserId(userId);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        return get(id, userId) != null && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);
        if (getAll(userId, LocalDate.MIN, LocalDate.MAX, LocalTime.MIN, LocalTime.MAX).contains(meal)) {
            return meal;
        } else
            return null;
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        log.info("getAll");
        return repository.values().stream()
                .filter(meal -> meal.getUserId().equals(userId) &&
                        fromDate.isBefore(meal.getDate()) &&
                        toDate.isAfter(meal.getDate()) &&
                        fromTime.isBefore(meal.getTime()) &&
                        toTime.isAfter(meal.getTime()))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}


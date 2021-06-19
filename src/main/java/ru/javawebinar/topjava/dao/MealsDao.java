package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealsDao {

    void addMeal(Meal meal);
    void deleteMeal(long id);
    void updateMeal(Meal meal);
    List<MealTo> getAllMeals();
    MealTo getById(long id);
}
